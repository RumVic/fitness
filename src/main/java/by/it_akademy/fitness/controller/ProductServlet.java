package by.it_akademy.fitness.controller;


import by.it_akademy.fitness.IDTO.InputDTO;
import by.it_akademy.fitness.service.api.IProductService;
import by.it_akademy.fitness.storage.entity.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/product")
public class ProductServlet {

    private final IProductService service;

    public ProductServlet(IProductService productService) {
        this.service = productService;
    }

    @RequestMapping(method = RequestMethod.GET)
    protected ResponseEntity<Product> getById (@RequestParam(name = "id") UUID id){
        return ResponseEntity.ok(service.read(id));
    }


    @RequestMapping
    protected ResponseEntity<List<? extends Product>> getList(){
        return ResponseEntity.ok(service.get());
    }


    @PostMapping
    protected ResponseEntity<Product> post(@RequestBody InputDTO idto) {
        Product created = this.service.create(idto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }


    @PutMapping("/{id}/dt_update/{dt_update}")
    protected ResponseEntity<Product> doPut(@PathVariable UUID id,
                                            @PathVariable("dt_update") long dtUpdateRaw,
                                            @RequestBody InputDTO idto){
        LocalDateTime dtUpdate = LocalDateTime.ofInstant(
                Instant.ofEpochMilli(dtUpdateRaw),
                ZoneId.of("UTC")
        );
        return ResponseEntity.ok(this.service.update(id, dtUpdate, idto));
    }
}
