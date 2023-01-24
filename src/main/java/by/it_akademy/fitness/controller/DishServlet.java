package by.it_akademy.fitness.controller;


import by.it_akademy.fitness.exception.LockException;
import by.it_akademy.fitness.idto.InputDishDTO;
import by.it_akademy.fitness.odto.OutPage;
import by.it_akademy.fitness.odto.OutputDishDTO;
import by.it_akademy.fitness.odto.OutputProductDTO;
import by.it_akademy.fitness.service.api.IDishService;
import by.it_akademy.fitness.storage.entity.Dish;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.http.HttpRequest;
import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequestMapping("/api/v1/recipe")
@RequiredArgsConstructor
public class DishServlet {

    @Autowired
    private final IDishService service;


    @PostMapping
    protected ResponseEntity<Dish> post(@RequestBody InputDishDTO idto , HttpServletRequest request) {
        final String authHeader = request.getHeader(AUTHORIZATION);
        Dish created = this.service.create(idto,authHeader);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }
    @GetMapping
    protected ResponseEntity<OutPage> getList(@RequestParam int page,
                                              @RequestParam int size) {
        Pageable pageable = PageRequest.of(page, size);
        OutPage<OutputDishDTO> dish = service.get(pageable);
        return new ResponseEntity<>(dish,HttpStatus.OK);
    }

    @PutMapping
    protected ResponseEntity<Dish> doPut(@RequestParam UUID id,
                                         @RequestParam(name = "dt_update") Long dt_update,
                                         @RequestBody InputDishDTO idto,
                                         HttpServletRequest request) throws LockException {
        final String authHeader = request.getHeader(AUTHORIZATION);
        return ResponseEntity.ok(this.service.update(id, dt_update, idto,authHeader));
    }

    //TODO NOT DEMANDED
    @GetMapping("/id")//http://localhost:8080/recipe/id + id param
    protected ResponseEntity<Dish> getById(@RequestParam(name = "id") UUID id) {
        return ResponseEntity.ok(service.read(id));
    }

}


