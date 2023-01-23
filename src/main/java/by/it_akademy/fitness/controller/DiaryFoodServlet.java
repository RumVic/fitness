package by.it_akademy.fitness.controller;

import by.it_akademy.fitness.exception.LockException;
import by.it_akademy.fitness.idto.InputDiaryFoodDTO;
import by.it_akademy.fitness.service.api.IDiaryFoodService;
import by.it_akademy.fitness.storage.entity.DiaryFood;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequestMapping("/api/v1/profile")
@RequiredArgsConstructor
public class DiaryFoodServlet {
    @Autowired
    private final IDiaryFoodService service;


    @PostMapping("/{uuid_profile}/journal/food")
    protected ResponseEntity<DiaryFood> post(@RequestBody InputDiaryFoodDTO idto,
                                             HttpServletRequest request,
                                             @PathVariable(name = "uuid_profile") UUID id) throws LockException {

        final String authHeader = request.getHeader(AUTHORIZATION);
        DiaryFood created = this.service.createWithParam(idto,authHeader,id);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/{uuid_profile}/journal/food")
    protected ResponseEntity<List<? extends DiaryFood>> getList(@PathVariable(name = "uuid_profile") UUID id) {
        return ResponseEntity.ok(service.getListOfLine(id));
    }

}
