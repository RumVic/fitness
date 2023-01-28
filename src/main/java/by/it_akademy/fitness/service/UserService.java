package by.it_akademy.fitness.service;

import by.it_akademy.fitness.exception.LockException;
import by.it_akademy.fitness.idto.InputUserByAdmin;
import by.it_akademy.fitness.idto.InputUserDTO;
import by.it_akademy.fitness.builder.UserBuilder;
import by.it_akademy.fitness.mappers.UserMapper;
import by.it_akademy.fitness.odto.OutPage;
import by.it_akademy.fitness.odto.OutputUserDTO;
import by.it_akademy.fitness.security.filter.JwtUtil;
import by.it_akademy.fitness.service.api.IAuditService;
import by.it_akademy.fitness.service.api.IUserService;
import by.it_akademy.fitness.storage.api.IUserStorage;
import by.it_akademy.fitness.storage.entity.User;
import by.it_akademy.fitness.util.enams.EntityType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.OptimisticLockException;
import java.time.Clock;
import java.util.UUID;

import static by.it_akademy.fitness.util.enams.EStatus.*;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService implements IUserService, UserDetailsService {


    private final String CREATED = "The User was created";

    private final String UPDATED = "The user was updated";

    private final String EDITED = "Value user's line was  updated somebody else";

    private final String LOCK = "Editing forbidden";

    private final String EXISTLOGIN = "The same Email already exist";


    private final BCryptPasswordEncoder passwordEncoder;

    private final JwtUtil jwtUtil;

    private final IUserStorage userStorage;

    private final IAuditService auditService;

    private final UserMapper userMapper;



    @Override
    public UserDetails loadUserByLogin(String login) throws UsernameNotFoundException {

        User user = userStorage.findByLogin(login);

        if (user == null) {
            log.error("User not found in the database");
            throw new UsernameNotFoundException("User not found in the database");
        } else {
            log.info("User found in the database:{}", login);
        }

        return user;
    }

    @Override
    @Transactional
    public UserDetails createNewUser(InputUserByAdmin dto) {
        validate(dto);
        User user = userStorage.save(UserBuilder
                .create()
                .setId(UUID.randomUUID())
                .setDtCrate(Clock.systemUTC().millis())
                .setDtUpdate(Clock.systemUTC().millis())
                .setUsername(dto.getNick())
                .setLogin(dto.getMail())
                .setPassword(passwordEncoder.encode(dto.getPassword()))
                .setRole(dto.getRole())
                .setStatus(dto.getStatus())
                .build());

        auditService.create(user, EntityType.USER, CREATED, user.getId().toString());

        return user;
    }

    @Override
    public OutputUserDTO readInput(UUID id) {
        User user = userStorage.findById(id).orElseThrow();
        return userMapper.onceMap(user);
    }

    @Override
    public User read(UUID id) {
        User user = userStorage.findById(id).orElseThrow();
        return user;
    }


    @Override
    public OutPage get(Pageable pageable) {
        Page<User> page = userStorage.findAll(pageable);
        return userMapper.map(page);
    }


    @Override
    @Transactional
    public User update(UUID id,
                       Long dtUpdate,
                       InputUserByAdmin item,
                       String header) throws LockException {

        validate(item);

        String login = extractCurrentToken(header);
        String mail = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = loadCurrentUserByLogin(mail);
        User readedUser = read(id);

        if(readedUser.getLogin().equals(item.getMail())){
            throw new IllegalStateException(EXISTLOGIN);
        }

        if (!readedUser.getUsername().equals(user.getLogin()) && !user.getRole().equals("ROLE_ADMIN")) {
            throw new LockException(LOCK);
        }

        if (!readedUser.getDtUpdate().equals(dtUpdate)) {
            throw new OptimisticLockException(EDITED);
        }



        User updateUser = userStorage.save(UserBuilder
                .create()
                .setId(id)
                .setDtCrate(readedUser.getDtCrate())
                .setDtUpdate(Clock.systemUTC().millis())
                .setUsername(item.getNick())
                .setLogin(item.getMail())
                .setPassword(passwordEncoder.encode(item.getPassword()))
                .setRole(item.getRole())
                .setStatus(item.getStatus())
                .build());

        auditService.create(user, EntityType.USER, UPDATED, updateUser.getId().toString());

        return updateUser;
    }



    public void validate(InputUserByAdmin dto){
        if(!dto.getRole().equals("ROLE_USER") && !dto.getRole().equals("ROLE_ADMIN")){
            throw new IllegalStateException("You need pass role properly");}
        if(!dto.getStatus().equals(ACTIVE) && !dto.getStatus().equals(WAITING_ACTIVATION) && !dto.getStatus().equals(DEACTIVATED)){
            throw new IllegalStateException("You need pass Status properly");}
    }

    @Override
    public OutputUserDTO getMyInfo(String header) {
        String currentLogin = extractCurrentToken(header);
        User user = userStorage.findByLogin(currentLogin);
        return userMapper.onceMap(user);
    }

    public String extractCurrentToken(String authHeader) {
        String jwtToken = authHeader.substring(7);
        String email = jwtUtil.extractUsername(jwtToken);
        UserDetails currentUser = loadUserByLogin(email);
        String currentLogin = currentUser.getUsername();
        return currentLogin;
    }

    public UUID extractCurrentUUID(String authHeader) {
        String jwtToken = authHeader.substring(7);
        String email = jwtUtil.extractUsername(jwtToken);
        UserDetails currentUserDetails = loadUserByLogin(email);
        String currentLogin = currentUserDetails.getUsername();
        User currentUser = userStorage.findByLogin(currentLogin);
        return currentUser.getId();
    }

    public User extractCurrentUserProfile(String authHeader) {
        String jwtToken = authHeader.substring(7);
        String email = jwtUtil.extractUsername(jwtToken);
        UserDetails currentUserDetails = loadUserByLogin(email);
        String currentLogin = currentUserDetails.getUsername();
        User currentUser = userStorage.findByLogin(currentLogin);
        return currentUser;
    }

    @Override
    public User loadCurrentUserByLogin(String login) {
        User crntUser = userStorage.findByLogin(login);
        return crntUser;
    }
    @Override
    public User update(UUID id,
                       Long dtUpdate,
                       InputUserDTO item,
                       String header) throws LockException {
        return null;
    }
    @Override
    public void delete(User user) {
    }
    @Override
    public User create(InputUserDTO dto, String header) {
        return null;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
