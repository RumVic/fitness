package by.it_akademy.fitness.service;

import by.it_akademy.fitness.builder.ProfileBuilder;
import by.it_akademy.fitness.exception.LockException;
import by.it_akademy.fitness.idto.InputProfileDTO;
import by.it_akademy.fitness.service.api.IProfileService;
import by.it_akademy.fitness.service.api.IUserService;
import by.it_akademy.fitness.storage.api.IProfileStorage;
import by.it_akademy.fitness.storage.entity.Profile;
import by.it_akademy.fitness.storage.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.util.List;
import java.util.UUID;


@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProfileService implements IProfileService {
    @Autowired
    private final IProfileStorage storage;
    @Autowired
    private final IUserService userService;


    @Override
    @Transactional
    public Profile create(InputProfileDTO dto, String header) {

        User currentUserProfile = userService.extractCurrentUserProfile(header);

        return storage.save(ProfileBuilder
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
    }

    @Override
    public Profile read(UUID id) {
        return storage.findById(id).orElseThrow();
    }

    @Override
    public List<Profile> get() {
        return null;
    }

    @Override
    @Transactional
    public Profile update(UUID id, Long dtUpdate, InputProfileDTO item, String header)throws LockException {
        return null;
    }

    @Override
    public void delete(Profile profile) {

    }
}
