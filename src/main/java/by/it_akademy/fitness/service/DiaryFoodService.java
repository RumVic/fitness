package by.it_akademy.fitness.service;

import by.it_akademy.fitness.exception.LockException;
import by.it_akademy.fitness.idto.InputDiaryFoodDTO;
import by.it_akademy.fitness.builder.DiaryFoodBuilder;
import by.it_akademy.fitness.mappers.DiaryFoodMapper;
import by.it_akademy.fitness.odto.OutPage;
import by.it_akademy.fitness.odto.OutputDiaryFoodDTO;
import by.it_akademy.fitness.service.api.*;
import by.it_akademy.fitness.storage.api.IDiaryFoodStorage;
import by.it_akademy.fitness.storage.entity.*;
import by.it_akademy.fitness.util.enams.EntityType;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.util.List;
import java.util.UUID;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DiaryFoodService implements IDiaryFoodService {

    private final String CREATED = "Line in food journal was created";
    private final String LOCK = "You can't create the line ";

    @Autowired
    private final IUserService userService;
    @Autowired
    private final IDiaryFoodStorage storage;
    @Autowired
    private final IDishService serviceDish;

    private final IAuditService auditService;

    private final IProfileService profileService;


    private final DiaryFoodMapper diaryFoodMapper;


    @Override
    @Transactional
    public DiaryFood createWithParam(InputDiaryFoodDTO dto, String header, UUID uuid) throws LockException {

        UUID uid = userService.extractCurrentUUID(header);
        String mail = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.loadCurrentUserByLogin(mail);
        Profile profile = profileService.read(uuid);
        Dish readedDish = serviceDish.read(dto.getDish().getId());

        if (!user.getId().equals(profile.getUser().getId())) {
                throw new LockException(LOCK);
        }

        DiaryFood diaryFood = storage.save(DiaryFoodBuilder
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

        auditService.create(
                user,
                EntityType.DIARY_FOOD,
                CREATED,
                diaryFood.getId().toString()
        );

        return diaryFood;
    }



    @Override
    public DiaryFood read(UUID id) {
        return storage.findById(id).orElseThrow();
    }

    @Override
    public OutPage<OutputDiaryFoodDTO> get(Pageable pageable, UUID id) {
        Page<DiaryFood> page = storage.findAllByProfile(pageable,id);
        return diaryFoodMapper.map(page);
    }

    @Override
    public List<DiaryFood> getListOfLine(UUID id) {
        return null; //storage.findByProfile(id);
    }

    @Override
    public OutPage get(Pageable pageable) {
        return null;
    }

    @Override
    @Transactional
    public DiaryFood update(UUID id, Long dtUpdate, InputDiaryFoodDTO item,String header)throws LockException {
        return null;
    }

    @Override
    public void delete(DiaryFood diaryFood) {}

    @Override
    @Transactional
    public DiaryFood create(InputDiaryFoodDTO dto,String header) {
        return null;
    }
}
