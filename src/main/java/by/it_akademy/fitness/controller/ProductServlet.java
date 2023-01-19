package by.it_akademy.fitness.controller;


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


   /* public ProductServlet(IProductService productService) {
        this.service = productService;
    }*/

    @GetMapping("/id")////http://localhost:8080/api/v1/product/id + id param
    protected ResponseEntity<Product> getById (@RequestParam(name = "id") UUID id){
        return ResponseEntity.ok(service.read(id));
    }


    @GetMapping("")
    protected ResponseEntity<List<? extends Product>> getList(){
        return ResponseEntity.ok(service.get());
    }


    @PostMapping
    protected ResponseEntity<Product> post(@RequestBody InputProductDTO idto, HttpServletRequest request) {
        final String authHeader = request.getHeader(AUTHORIZATION);
        Product created = this.service.create(idto,authHeader);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping
    protected ResponseEntity<Product> doPut(@RequestParam UUID id,
                                            @RequestParam(name = "dt_update") Long dt_update,
                                            @RequestBody InputProductDTO idto){
        /*LocalDateTime dtUpdate = LocalDateTime.ofInstant(
                Instant.ofEpochMilli(dtUpdateRaw),
                ZoneId.of("UTC")
        );*/
        return ResponseEntity.ok(this.service.update(id, dt_update, idto));
    }
}
