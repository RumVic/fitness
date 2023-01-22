package by.it_akademy.fitness.service;

import by.it_akademy.fitness.exception.LockException;
import by.it_akademy.fitness.idto.InputUserDTO;
import by.it_akademy.fitness.builder.UserBuilder;
import by.it_akademy.fitness.security.filter.JwtUtil;
import by.it_akademy.fitness.service.api.IUserService;
import by.it_akademy.fitness.storage.api.IUserStorage;
import by.it_akademy.fitness.storage.entity.User;
import by.it_akademy.fitness.util.EStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService implements IUserService, UserDetailsService {

    private final BCryptPasswordEncoder passwordEncoder;

    private final JwtUtil jwtUtil;

    private final IUserStorage userStorage;
    //@Autowired
    //private final RoleRepo roleRepo;
    //@Autowired
    //private final PasswordEncoder passwordncoder;

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
    public UserDetails createNewUser(InputUserDTO dto) {
        return userStorage.save(UserBuilder
                .create()
                .setId(UUID.randomUUID())
                .setDtCrate(Clock.systemUTC().millis())
                .setDtUpdate(Clock.systemUTC().millis())
                .setUsername(dto.getNick())
                .setLogin(dto.getMail())
                .setPassword(passwordEncoder.encode(dto.getPassword()))
                .setRole("ROLE_USER")
                .setStatus(EStatus.ACTIVE)
                .build());
    }

    @Override
    public User create(InputUserDTO dto, String header) {
        return null;
    }

    @Override
    public User read(UUID id) {
        return userStorage.findById(id).orElseThrow();
    }

    @Override
    public List<User> get() {
        return userStorage.findAll();
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
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

    @Override
    public User getMyInfo(String header) {
        String currentLogin = extractCurrentToken(header);
        return userStorage.findByLogin(currentLogin);
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
}
