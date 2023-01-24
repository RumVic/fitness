package by.it_akademy.fitness.service;


import by.it_akademy.fitness.exception.LockException;
import by.it_akademy.fitness.idto.InputComDishDTO;
import by.it_akademy.fitness.idto.InputDishDTO;
import by.it_akademy.fitness.builder.DishBuilder;
import by.it_akademy.fitness.mappers.DishMapper;
import by.it_akademy.fitness.odto.OutPage;
import by.it_akademy.fitness.service.api.IAuditService;
import by.it_akademy.fitness.service.api.ICompositionDishService;
import by.it_akademy.fitness.service.api.IDishService;
import by.it_akademy.fitness.service.api.IUserService;
import by.it_akademy.fitness.storage.api.IDishStorage;
import by.it_akademy.fitness.storage.entity.CompositionDish;
import by.it_akademy.fitness.storage.entity.Dish;
import by.it_akademy.fitness.storage.entity.User;
import by.it_akademy.fitness.util.enams.EntityType;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.OptimisticLockException;
import java.time.Clock;
import java.util.List;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DishService implements IDishService {

    private final String CREATED = "Line in food journal was created";

    private final String UPDATED = "Line in food journal was updated";

    private final String EDITED = "Line already edited by somebody else";

    private final String LOCK = "Editing forbidden";

    @Autowired
    private final IDishStorage storage;
    @Autowired
    private final IUserService userService;
    @Autowired
    private final ICompositionDishService service;

    private final IAuditService auditService;

    private final DishMapper dishMapper;

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

        String mail = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.loadCurrentUserByLogin(mail);

        Dish dish = storage.save(DishBuilder
                .create()
                .setId(idDish)
                .setDtCreate(Clock.systemUTC().millis())
                .setDtUpdate(Clock.systemUTC().millis())
                .setTitle(idto.getTitle())
                .setCompositionDish(service.create(list, idDish))
                .setCreateByRole(login)
                .build()
        );

        auditService.create(
                user,
                EntityType.DISH,
                CREATED,
                dish.getId().toString());

        return dish;
    }

    public Dish read(UUID uuid) {
        return storage.findById(uuid).orElseThrow();
    }



    @Override
    public OutPage get(Pageable pageable) {
        Page<Dish> dishPage = storage.findAll(pageable);
        return dishMapper.map(dishPage) ;
    }

    @Override
    @Transactional
    public Dish update(UUID id, Long dtUpdate, InputDishDTO idto,String header) throws LockException {

        String login = userService.extractCurrentToken(header);

        String mail = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.loadCurrentUserByLogin(mail);

        Dish readed = storage.findById(id).orElseThrow();
        List<CompositionDish> readedList = readed.getCompositionDishList();

        if (!readed.getCreateByRole().equals(user.getLogin())) {
            if (!user.getRole().equals("ROLE_ADMIN")) {
                throw new LockException(LOCK);
            }
        }
        if (!readed.getDtUpdate().equals(dtUpdate)) {
            throw new OptimisticLockException(EDITED);
        }

        service.delete(readedList);

        List<InputComDishDTO> list = idto.getComDishDTO();

        Dish dishUpdate = storage.save(DishBuilder
                .create()
                .setId(id)
                .setDtCreate(readed.getDtCreate())
                .setDtUpdate(Clock.systemUTC().millis())
                .setCompositionDish((service.update(list, id)))
                .setTitle(idto.getTitle())
                .setCreateByRole(login)
                .build());

        auditService.create(user, EntityType.DISH, UPDATED, dishUpdate.getId().toString());

        return dishUpdate;
    }

    @Override
    public void delete(Dish dish) {
    }
}

