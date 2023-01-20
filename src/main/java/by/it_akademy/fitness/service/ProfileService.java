package by.it_akademy.fitness.service;

import by.it_akademy.fitness.idto.InputProfileDTO;
import by.it_akademy.fitness.service.api.IProfileService;
import by.it_akademy.fitness.storage.api.IProfileStorage;
import by.it_akademy.fitness.storage.entity.Profile;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;


@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProfileService implements IProfileService {

    private final IProfileStorage storage;



    @Override
    @Transactional
    public Profile create(InputProfileDTO dto, String header) {
        return null;
    }

    @Override
    public Profile read(UUID id) {
        return null;
    }

    @Override
    public List<Profile> get() {
        return null;
    }

    @Override
    @Transactional
    public Profile update(UUID id, Long dtUpdate, InputProfileDTO item, String header) {
        return null;
    }

    @Override
    public void delete(Profile profile) {

    }
}
