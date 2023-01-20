package by.it_akademy.fitness.service;


import by.it_akademy.fitness.idto.InputComDishDTO;
import by.it_akademy.fitness.idto.InputDishDTO;
import by.it_akademy.fitness.builder.DishBuilder;
import by.it_akademy.fitness.service.api.ICompositionDishService;
import by.it_akademy.fitness.service.api.IDishService;
import by.it_akademy.fitness.service.api.IUserService;
import by.it_akademy.fitness.storage.api.IDishStorage;
import by.it_akademy.fitness.storage.api.IUserStorage;
import by.it_akademy.fitness.storage.entity.CompositionDish;
import by.it_akademy.fitness.storage.entity.Dish;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.util.List;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DishService implements IDishService {
    @Autowired
    private final IDishStorage storage;
    @Autowired
    private final IUserService userService;
    @Autowired
    private final ICompositionDishService service;

   /* public DishService(IDishStorage dishStorage, ICompositionDishService service) {
        this.storage = dishStorage;
        this.service = service;
    }*/

    @Override
    @Transactional
    public Dish create(InputDishDTO idto, String header) {

        String login = userService.extractCurrentToken(header);

        List<InputComDishDTO> list = idto.getComDishDTO();

        UUID idDish = UUID.randomUUID();

        return storage.save(DishBuilder
                .create()
                .setId(idDish)
                .setDtCreate(Clock.systemUTC().millis())
                .setDtUpdate(Clock.systemUTC().millis())
                .setTitle(idto.getTitle())
                .setCompositionDish(service.create(list, idDish))
                .setCreateByRole(login)
                .build()
        );
    }

    public Dish read(UUID uuid) {
        return storage.findById(uuid).orElseThrow();
    }

    @Override
    public List<Dish> get() {
        return storage.findAll();
    }

    @Override
    @Transactional
    public Dish update(UUID id, Long dtUpdate, InputDishDTO idto,String header) {

        String login = userService.extractCurrentToken(header);

        Dish readed = storage.findById(id).orElseThrow();

        List<CompositionDish> readedList = readed.getCompositionDishList();

        service.delete(readedList);

        if (readed == null) {
            throw new IllegalArgumentException("Меню не найдено");
        }

        if (!readed.getDtUpdate().equals(dtUpdate)) {
            throw new IllegalArgumentException("К сожалению меню уже было отредактировано кем-то другим");
        }

        List<InputComDishDTO> list = idto.getComDishDTO();

        Dish dishUpdate = DishBuilder
                .create()
                .setId(id)
                .setDtCreate(readed.getDtCreate())
                .setDtUpdate(Clock.systemUTC().millis())
                .setCompositionDish((service.update(list, id)))
                .setTitle(idto.getTitle())
                .setCreateByRole(login)
                .build();
        return storage.save(dishUpdate);
    }

    @Override
    public void delete(Dish dish) {

    }
}

