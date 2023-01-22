package by.it_akademy.fitness.controller;

import by.it_akademy.fitness.idto.InputUserDTO;
import by.it_akademy.fitness.security.filter.JwtUtil;
import by.it_akademy.fitness.service.UserService;
import by.it_akademy.fitness.storage.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.UUID;



@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class AdminServlet {

    @Autowired
    private final UserService service;
    @Autowired
    private final JwtUtil jwtUtil;

    @PostMapping("/users")
    public ResponseEntity<String> registrationByAdmin(@RequestBody @Valid InputUserDTO inputUserDTO) {
        UserDetails created = this.service.createNewUser(inputUserDTO);
        return ResponseEntity.ok(jwtUtil.generateToken(created,inputUserDTO.getMail()));

        //TODO change response - it have not sent jwt token - sent it through email
    }

    @GetMapping(path = "/users", params = "uuid")
    public ResponseEntity<User> getUserById(@RequestParam(name = "uuid") UUID uuid){
        return ResponseEntity.ok(service.read(uuid));
    }

    @GetMapping("/users")
    public ResponseEntity<? extends List<User>> getUsers(HttpServletRequest request){
        return ResponseEntity.ok(service.get());
    }
}
