package by.it_akademy.fitness.controller;


import by.it_akademy.fitness.IDTO.InputDishDTO;
import by.it_akademy.fitness.service.api.IDishService;
import by.it_akademy.fitness.storage.entity.Dish;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/recipe")//http://localhost:8080/recipe
public class DishServlet {


    private final IDishService service;

    public DishServlet(IDishService dishService) {
        this.service = dishService;
    }

    @GetMapping("/id")//http://localhost:8080/recipe/id + id param
    protected ResponseEntity<Dish> getById(@RequestParam(name = "id") UUID id) {
        return ResponseEntity.ok(service.read(id));
    }


    @GetMapping("")
    protected ResponseEntity<List<? extends Dish>> getList() {
        return ResponseEntity.ok(service.get());
    }


    @PostMapping
    protected ResponseEntity<Dish> post(@RequestBody InputDishDTO idto) {
        Dish created = this.service.create(idto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping
    protected ResponseEntity<Dish> doPut(@RequestParam UUID id,
                                         @RequestParam(name = "dt_update") Long dt_update,
                                         @RequestBody InputDishDTO idto) {

        /*LocalDateTime dtUpdate = LocalDateTime.ofInstant(
                Instant.ofEpochMilli(dtUpdateRaw),
                ZoneId.of("UTC")
        );*/

        return ResponseEntity.ok(this.service.update(id, dt_update, idto));
    }
}


