package by.it_akademy.fitness.service.api;

import by.it_akademy.fitness.odto.OutPage;
import by.it_akademy.fitness.odto.OutputAuditDTO;
import by.it_akademy.fitness.storage.entity.Audit;
import by.it_akademy.fitness.storage.entity.User;
import by.it_akademy.fitness.util.enams.EntityType;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface IAuditService {

    Audit create(User user, EntityType entityType,String text, String idEntity);

    OutPage<OutputAuditDTO> get(Pageable pag);

    Audit read (UUID uuid);

    List<OutputAuditDTO> getById(String uuid);


}
