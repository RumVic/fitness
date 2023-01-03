package by.it_akademy.fitness.service;


import by.it_akademy.fitness.IDTO.InputDTOComDish;
import by.it_akademy.fitness.IDTO.InputDTODish;
import by.it_akademy.fitness.builder.DishBuilder;
import by.it_akademy.fitness.service.api.ICompositionDishService;
import by.it_akademy.fitness.service.api.IDishService;
import by.it_akademy.fitness.storage.api.IDishStorage;
import by.it_akademy.fitness.storage.entity.Dish;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.util.List;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class DishService implements IDishService {

    private final IDishStorage storage;
    private final ICompositionDishService service;

    public DishService(IDishStorage dishStorage, ICompositionDishService service) {
        this.storage = dishStorage;
        this.service = service;
    }

    @Override
    @Transactional
    public Dish create(InputDTODish idto) {

      InputDTOComDish compositionDish = idto.getCompositionDishObject();

        return storage.save(DishBuilder
                .create()
                .setId(UUID.randomUUID())
                .setDtCreate(Clock.systemUTC().millis())
                .setDtUpdate(Clock.systemUTC().millis())
                .setTitle(idto.getTitle())
                .setCompositionDish(service.create(compositionDish))
                .build()
        );
    }

    public Dish read (UUID uuid) {
        return storage.findById(uuid).orElseThrow();
    }

    @Override
    public List<Dish> get() {
        return storage.findAll();
    }

    @Override
    @Transactional
    public Dish update(UUID id, Long dtUpdate, InputDTODish idto) {
        return null;
      /*  Dish readed = storage.findById(id).orElseThrow();

        if (readed == null) {
            throw new IllegalArgumentException("Меню не найдено");
        }


        if (!readed.getDtUpdate().equals(dtUpdate)) {
            throw new IllegalArgumentException("К сожалению меню уже было отредактировано кем-то другим");
        }

        Dish dishUpdate = DishBuilder
                .create()
                .setId(id)
                .setDtCreate(readed.getDtCreate())
                .setDtUpdate(dtUpdate)
                .setCompositionDish((service.create(compositionDish)))
                .build();
        return storage.save(dishUpdate);

       */
    }
}

