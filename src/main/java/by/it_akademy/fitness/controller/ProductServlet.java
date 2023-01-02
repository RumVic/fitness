package by.it_akademy.fitness.controller;


import by.it_akademy.fitness.IDTO.InputDTO;
import by.it_akademy.fitness.service.api.IProductService;
import by.it_akademy.fitness.storage.entity.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/product")//http://localhost:8080/product
public class ProductServlet {

    private final IProductService service;

    public ProductServlet(IProductService productService) {
        this.service = productService;
    }

    @GetMapping("/id")////http://localhost:8080/product/id + id param
    protected ResponseEntity<Product> getById (@RequestParam(name = "id") UUID id){
        return ResponseEntity.ok(service.read(id));
    }


    @GetMapping("")
    protected ResponseEntity<List<? extends Product>> getList(){
        return ResponseEntity.ok(service.get());
    }


    @PostMapping
    protected ResponseEntity<Product> post(@RequestBody InputDTO idto) {
        Product created = this.service.create(idto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping
    protected ResponseEntity<Product> doPut(@RequestParam UUID id,
                                            @RequestParam(name = "dt_update") Long dt_update,
                                            @RequestBody InputDTO idto){
        
        /*LocalDateTime dtUpdate = LocalDateTime.ofInstant(
                Instant.ofEpochMilli(dtUpdateRaw),
                ZoneId.of("UTC")
        );*/

        return ResponseEntity.ok(this.service.update(id, dt_update, idto));
    }
}
