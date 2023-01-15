package by.it_akademy.fitness.service;

import by.it_akademy.fitness.IDTO.InputDiaryFoodDTO;
import by.it_akademy.fitness.builder.DiaryFoodBuilder;
import by.it_akademy.fitness.service.api.IDiaryFoodService;
import by.it_akademy.fitness.service.api.IDishService;
import by.it_akademy.fitness.service.api.IProductService;
import by.it_akademy.fitness.storage.api.IDiaryFoodStorage;
import by.it_akademy.fitness.storage.entity.DiaryFood;
import by.it_akademy.fitness.storage.entity.Dish;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.util.List;
import java.util.UUID;


@Service
@Transactional(readOnly = true)
public class DiaryFoodService implements IDiaryFoodService {

    private final IDiaryFoodStorage storage;

    private final IDishService serviceDish;

    private final IProductService serviceProduct;

    public DiaryFoodService(IDiaryFoodStorage storage,
                            IDishService serviceDish,
                            IProductService serviceProduct) {
        this.storage = storage;
        this.serviceDish = serviceDish;
        this.serviceProduct = serviceProduct;
    }

    @Override
    @Transactional
    public DiaryFood create(InputDiaryFoodDTO dto) {

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
                .setProfile(dto.getProfile())
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
    public DiaryFood update(UUID id, Long dtUpdate, InputDiaryFoodDTO item) {
        return null;
    }

    @Override
    public void delete(DiaryFood diaryFood) {

    }
}
