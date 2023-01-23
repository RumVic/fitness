package by.it_akademy.fitness.controller;


import by.it_akademy.fitness.exception.LockException;
import by.it_akademy.fitness.idto.InputProductDTO;
import by.it_akademy.fitness.security.filter.JwtUtil;
import by.it_akademy.fitness.service.api.IProductService;
import by.it_akademy.fitness.storage.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequestMapping("/api/v1/product")//http://localhost:8080/product
@RequiredArgsConstructor
public class ProductServlet {
    @Autowired
    private final IProductService service;
    @Autowired
    private final JwtUtil jwtUtil;


    @PostMapping
    protected ResponseEntity<Product> post(@RequestBody InputProductDTO idto, HttpServletRequest request) {
        final String authHeader = request.getHeader(AUTHORIZATION);
        Product created = this.service.create(idto, authHeader);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }
    @GetMapping("")
    protected ResponseEntity<List<? extends Product>> getList() {
        return ResponseEntity.ok(service.get());
    }
    @PutMapping("/{uuid}/dt_update/{dt_update}")
    protected ResponseEntity<Product> doPut(@PathVariable(name = "uuid") UUID id,
                                            @PathVariable(name = "dt_update") Long dt_update,
                                            @RequestBody InputProductDTO idto,
                                            HttpServletRequest request) throws LockException {
        final String authHeader = request.getHeader(AUTHORIZATION);
        return ResponseEntity.ok(this.service.update(id, dt_update, idto, authHeader));
    }

    //TODO NOT DEMANDED
    @GetMapping("/id")////http://localhost:8080/api/v1/product/id + id param
    protected ResponseEntity<Product> getById(@RequestParam(name = "id") UUID id) {
        return ResponseEntity.ok(service.read(id));
    }
}
