package by.it_akademy.fitness.service;

import by.it_akademy.fitness.IDTO.InputDTOProduct;
import by.it_akademy.fitness.builder.ProductBuilder;
import by.it_akademy.fitness.service.api.IProductService;
import by.it_akademy.fitness.storage.api.IProductStorage;
import by.it_akademy.fitness.storage.entity.Product;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
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
    public Product create(InputDTOProduct idto) {
        return storage.save(ProductBuilder
                .create()
                .setId(UUID.randomUUID())
                .setDtCreate(Clock.systemUTC().millis())
                .setDtUpdate(Clock.systemUTC().millis())
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
        return storage.findAll();
    }

    @Override
    @Transactional
    public Product update(UUID id, Long dtUpdate, InputDTOProduct idto) {

    Product readed = storage.findById(id).orElseThrow();

        if (readed == null) {
            throw new IllegalArgumentException("Меню не найдено");
        }


        if (!readed.getDtUpdate().equals(dtUpdate)) {
            throw new IllegalArgumentException("К сожалению меню уже было отредактировано кем-то другим");
        }

        Product productUpdate = ProductBuilder
                .create()
                .setId(readed.getId())
                .setDtCreate(readed.getDtCreate())
                .setDtUpdate(Clock.systemUTC().millis())
                .setTitle(idto.getTitle())
                .setWeight(idto.getWeight())
                .setCalories(idto.getCalories())
                .setProteins(idto.getProteins())
                .setFats(idto.getFats())
                .setCarbohydrates(idto.getCarbohydrates())
                .build();
        return storage.save(productUpdate);
    }
}
