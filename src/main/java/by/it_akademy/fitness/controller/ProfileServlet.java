package by.it_akademy.fitness.controller;

import by.it_akademy.fitness.idto.InputProfileDTO;
import by.it_akademy.fitness.service.api.IProfileService;
import by.it_akademy.fitness.storage.entity.Profile;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import java.util.UUID;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequestMapping("api/v1/profile")
@RequiredArgsConstructor
public class ProfileServlet {

    private final IProfileService service;

    @PostMapping
    protected ResponseEntity<Profile> post(@RequestBody InputProfileDTO idto, HttpServletRequest request) {
        final String authHeader = request.getHeader(AUTHORIZATION);
        Profile created = this.service.create(idto,authHeader);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }
    @GetMapping("/{uuid_profile}")
    protected ResponseEntity<Profile> getById (@PathVariable(name = "uuid_profile") UUID id){
        return ResponseEntity.ok(service.read(id));
    }


    
}

