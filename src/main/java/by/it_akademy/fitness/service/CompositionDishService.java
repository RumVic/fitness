package by.it_akademy.fitness.service;

import by.it_akademy.fitness.IDTO.InputDTOComDish;
import by.it_akademy.fitness.builder.CompositionDishBuilder;
import by.it_akademy.fitness.service.api.ICompositionDishService;
import by.it_akademy.fitness.storage.api.ICompositionDishStorage;
import by.it_akademy.fitness.storage.entity.CompositionDish;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.util.List;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class CompositionDishService implements ICompositionDishService {

    private final ICompositionDishStorage storage;

    public CompositionDishService(ICompositionDishStorage storage) {
        this.storage = storage;
    }

    @Override
    @Transactional
    public CompositionDish create(InputDTOComDish dto) {

        return storage.save(CompositionDishBuilder
                .create()
                .setId(UUID.randomUUID())
                .setDtCreate(Clock.systemUTC().millis())
                .setDtUpdate((Clock.systemUTC().millis()))
                .setTitle(dto.getTitle())
                .setDish(UUID.randomUUID())//неправильно
                .setProductList(dto.getProductList())
                .setWeight(dto.getWeight())
                .build()
        );
    }

    @Override
    public CompositionDish read(UUID id) {
        return null;
    }

    @Override
    public List<CompositionDish> get() {
        return null;
    }

    @Override
    public CompositionDish update(UUID id, Long dtUpdate, InputDTOComDish item) {
        return null;
    }
}
