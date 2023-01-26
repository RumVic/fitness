package by.it_akademy.fitness.service;

import by.it_akademy.fitness.builder.ProfileBuilder;
import by.it_akademy.fitness.exception.LockException;
import by.it_akademy.fitness.idto.InputProfileDTO;
import by.it_akademy.fitness.mappers.ProfileMapper;
import by.it_akademy.fitness.odto.OutPage;
import by.it_akademy.fitness.odto.OutputProfileDTO;
import by.it_akademy.fitness.service.api.IAuditService;
import by.it_akademy.fitness.service.api.IProfileService;
import by.it_akademy.fitness.service.api.IUserService;
import by.it_akademy.fitness.storage.api.IProfileStorage;
import by.it_akademy.fitness.storage.entity.Profile;
import by.it_akademy.fitness.storage.entity.User;
import by.it_akademy.fitness.util.enams.EntityType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.Clock;
import java.util.UUID;


@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProfileService implements IProfileService {

    private final String CREATED = "New user's profile was created";


    @Autowired
    private final IProfileStorage storage;
    @Autowired
    private final IUserService userService;

    private final IAuditService auditService;

    private final ProfileMapper profileMapper;


    @Override
    @Transactional
    public Profile create(InputProfileDTO dto, String header) {

        User currentUserProfile = userService.extractCurrentUserProfile(header);

        String mail = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.loadCurrentUserByLogin(mail);



        Profile profile = storage.save(ProfileBuilder
                .create()
                .setId(UUID.randomUUID())
                .setDtCreate(Clock.systemUTC().millis())
                .setDtUpdate(Clock.systemUTC().millis())
                .setUser(currentUserProfile)
                .setHeight(dto.getHeight())
                .setWeight(dto.getWeight())
                .setBirthday(dto.getBirthday())
                .setGender(dto.getSex())
                .setLifestyle(dto.getActivity_type())
                .setTargetWeight(dto.getTarget())
                .build());

        auditService.create(
                user,
                EntityType.PROFILE,
                CREATED,
                profile.getId().toString()
        );

        return profile;
    }

    @Override
    public OutputProfileDTO readById(UUID id) {
        Profile profile = storage.findById(id).orElseThrow();
        return profileMapper.map(profile);
    }

    @Override
    public Profile read(UUID id) {
        return storage.findById(id).orElseThrow();
    }

    @Override
    public OutPage get(Pageable pageable) {
        return null;
    }

    @Override
    @Transactional
    public Profile update(UUID id, Long dtUpdate, InputProfileDTO item, String header)throws LockException {
        return null;}

    @Override
    public void delete(Profile profile) {
    }
}
