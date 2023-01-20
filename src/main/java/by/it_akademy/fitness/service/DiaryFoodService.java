package by.it_akademy.fitness.service;

import by.it_akademy.fitness.idto.InputDiaryFoodDTO;
import by.it_akademy.fitness.builder.DiaryFoodBuilder;
import by.it_akademy.fitness.service.api.IDiaryFoodService;
import by.it_akademy.fitness.service.api.IDishService;
import by.it_akademy.fitness.service.api.IProductService;
import by.it_akademy.fitness.service.api.IUserService;
import by.it_akademy.fitness.storage.api.IDiaryFoodStorage;
import by.it_akademy.fitness.storage.entity.DiaryFood;
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
public class DiaryFoodService implements IDiaryFoodService {

    @Autowired
    private final IUserService userService;
    @Autowired
    private final IDiaryFoodStorage storage;
    @Autowired
    private final IDishService serviceDish;
    @Autowired
    private final IProductService serviceProduct;

  /*  public DiaryFoodService(IDiaryFoodStorage storage,
                            IDishService serviceDish,
                            IProductService serviceProduct) {
        this.storage = storage;
        this.serviceDish = serviceDish;
        this.serviceProduct = serviceProduct;
    }*/

    @Override
    @Transactional
    public DiaryFood create(InputDiaryFoodDTO dto,String header) {

        UUID uuid = userService.extractCurrentUUID(header);


        Dish readedDish = serviceDish.read(dto.getDish().getId());

        return storage.save(DiaryFoodBuilder
                .create()
                .setId(UUID.randomUUID())
                .setDtCreate(Clock.systemUTC().millis())
                .setDtUpdate(Clock.systemUTC().millis())
                .setDtSupply(dto.getDtSupply())
                .setDish(readedDish)
                .setWeightDish(dto.getWeightDish())
                .setProduct(dto.getProduct())
                .setWeightProduct(dto.getWeightProduct())
                .setProfile(uuid)
                .build());
    }

    @Override
    public DiaryFood read(UUID id) {
        return storage.findById(id).orElseThrow();
    }

    @Override
    public List<DiaryFood> get() {
        return storage.findAll();
    }

    @Override
    @Transactional
    public DiaryFood update(UUID id, Long dtUpdate, InputDiaryFoodDTO item,String header) {
        return null;
    }

    @Override
    public void delete(DiaryFood diaryFood) {

    }
}
