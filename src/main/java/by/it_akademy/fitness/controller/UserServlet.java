package by.it_akademy.fitness.controller;

import by.it_akademy.fitness.idto.InputUserDTO;
import by.it_akademy.fitness.security.filter.JwtUtil;
import by.it_akademy.fitness.service.UserService;
import by.it_akademy.fitness.storage.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class UserServlet {
    @Autowired
    private final AuthenticationManager authenticationManager;
    @Autowired
    private final UserService service;
    @Autowired
    private final JwtUtil jwtUtil;

    @PostMapping("/registration")
    public ResponseEntity<String> registration(@RequestBody InputUserDTO inputUserDTO) {
        UserDetails created = this.service.createNewUser(inputUserDTO);
        return ResponseEntity.ok(jwtUtil.generateToken(created,inputUserDTO.getMail()));
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginIn(@RequestBody InputUserDTO request ){

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getMail(), request.getPassword()));

        final UserDetails userDetails = service.loadUserByLogin(request.getMail());

        if (userDetails != null) {
            return ResponseEntity.ok(jwtUtil.generateToken(userDetails,request.getMail()));
        }
        return ResponseEntity.status(400).body("Some error has occurred");
    }

    @GetMapping("/me")
        public ResponseEntity<User> getMe(HttpServletRequest request) {
        final String authHeader = request.getHeader(AUTHORIZATION);
        return ResponseEntity.ok(service.getMyInfo(authHeader));

    }

}
