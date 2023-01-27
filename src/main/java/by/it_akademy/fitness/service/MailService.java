package by.it_akademy.fitness.service;

import by.it_akademy.fitness.builder.UserBuilder;
import by.it_akademy.fitness.idto.InputUserDTO;
import by.it_akademy.fitness.mail.MailSender;
import by.it_akademy.fitness.storage.api.IUserStorage;
import by.it_akademy.fitness.storage.entity.User;
import by.it_akademy.fitness.util.enams.EStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.Clock;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MailService implements  UserDetailsService {

    private final IUserStorage storage;

    @Autowired
    private MailSender mailSender;


    private final BCryptPasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

    public User addUser(InputUserDTO user) {


        User createdUser = UserBuilder   //create User from [assed json
                .create()
                .setId(UUID.randomUUID())
                .setDtCrate(Clock.systemUTC().millis())
                .setDtUpdate(Clock.systemUTC().millis())
                .setUsername(user.getNick())
                .setLogin(user.getMail())
                .setPassword(passwordEncoder.encode(user.getPassword()))
                .setRole("ROLE_USER")
                .setStatus(EStatus.WAITING_ACTIVATION)
                .setActivationCode(UUID.randomUUID().toString())
                .build();


        User userFromDb = storage.findByLogin(user.getMail()); //find user from DB
        if (userFromDb != null) {
            throw new IllegalArgumentException("The user with the same login already exist");
        } User savedUser = storage.save(createdUser);


        if (user.getMail() != null) {

            String message = ("Hello. Welcome to Fitness. " +
                    "Please go here to finish registration." +
                    "http://localhost:8080/activation/"
                    +savedUser.getActivationCode()
                    +"/mail/"
                    +user.getMail());

            mailSender.send(user.getMail(), "ActivationCode", message);
        }
        return savedUser;
    }

    public User activateUser(String code) throws IOException {

        User user = storage.findByActivationCode(code);

        if (user == null) {
            throw new IOException("We are apologize , try to registration again");
        } user.setActivationCode(null);

        return storage.save(user);
    }

}
