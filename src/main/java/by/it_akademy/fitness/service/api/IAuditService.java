package by.it_akademy.fitness.service.api;

import by.it_akademy.fitness.storage.entity.Audit;
import by.it_akademy.fitness.storage.entity.User;
import by.it_akademy.fitness.util.EntityType;

import java.util.List;
import java.util.UUID;

public interface IAuditService {

    Audit create(User user, EntityType entityType,String text, String idEntity);

    List<Audit> get();

    Audit read (UUID uuid);

    List<Audit> getById(String uuid);
}
