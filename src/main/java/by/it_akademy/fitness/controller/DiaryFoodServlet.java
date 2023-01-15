package by.it_akademy.fitness.controller;

import by.it_akademy.fitness.IDTO.InputDiaryFoodDTO;
import by.it_akademy.fitness.service.api.IDiaryFoodService;
import by.it_akademy.fitness.storage.entity.DiaryFood;
import by.it_akademy.fitness.storage.entity.Dish;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/journal")
public class DiaryFoodServlet {

    private final IDiaryFoodService service;

    public DiaryFoodServlet(IDiaryFoodService service) {
        this.service = service;
    }

    @PostMapping
    protected ResponseEntity<DiaryFood> post(@RequestBody InputDiaryFoodDTO idto) {
        DiaryFood created = this.service.create(idto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("")
    protected ResponseEntity<List<? extends DiaryFood>> getList() {
        return ResponseEntity.ok(service.get());
    }

}
