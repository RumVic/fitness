package by.it_akademy.fitness.service;

import by.it_akademy.fitness.builder.AuditBuilder;
import by.it_akademy.fitness.service.api.IAuditService;
import by.it_akademy.fitness.storage.api.IAuditStorage;
import by.it_akademy.fitness.storage.entity.Audit;
import by.it_akademy.fitness.storage.entity.User;
import by.it_akademy.fitness.util.EntityType;
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
public class AuditService implements IAuditService {
    @Autowired
    private final IAuditStorage storage;


    @Override
    @Transactional
    public Audit create(User user,
                        EntityType entityType,
                        String text,
                        String idEntity) {

        return storage.save(
                AuditBuilder
                        .create()
                        .setId(UUID.randomUUID())
                        .setDtCreate(Clock.systemUTC().millis())
                        .setDtUpdate(Clock.systemUTC().millis())
                        .setText(text)
                        .setType(entityType)
                        .setUid(idEntity)
                        .setUser(user)
                        .build());
    }

    @Override
    public List<Audit> get() {
        return storage.findAll();
    }

    @Override
    public List<Audit> getById(String uuid) {
        //return storage.findById(uuid).orElseThrow();
        return storage.findByUid(uuid);
    }

    @Override
    public Audit read(UUID uuid) {
        return null;
    }
}
