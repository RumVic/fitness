package by.it_akademy.fitness.service;

import by.it_akademy.fitness.IDTO.InputDTO;
import by.it_akademy.fitness.controller.builder.ProductBuilder;
import by.it_akademy.fitness.service.api.IProductService;
import by.it_akademy.fitness.storage.api.IProductStorage;
import by.it_akademy.fitness.storage.entity.Product;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class ProductService implements IProductService {

    private final IProductStorage storage;

    public ProductService(IProductStorage productStorage) {
        this.storage = productStorage;
    }

    @Override
    @Transactional
    public Product create(InputDTO idto) {
        return storage.save(ProductBuilder
                .create()
                .setId(UUID.randomUUID())
                .setDtCreate(LocalDateTime.now())
                .setDtUpdate(LocalDateTime.now())
                .setTitle(idto.getTitle())
                .setWeight(idto.getWeight())
                .setCalories(idto.getCalories())
                .setProteins(idto.getProteins())
                .setFats(idto.getFats())
                .setCarbohydrates(idto.getCarbohydrates())
                .build());
    }

    public Product read (UUID uuid) {
        return storage.findById(uuid).orElseThrow();
    }

    @Override
    public List<Product> get() {
        return null;
    }

    @Override
    public Product update(UUID id, LocalDateTime dtUpdate, InputDTO item) {
        return null;
    }

}
