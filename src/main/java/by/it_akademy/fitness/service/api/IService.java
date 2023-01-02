package by.it_akademy.fitness.service.api;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface IService<ENTITY, IDTO, ODTO> {


    ENTITY create(IDTO dto);

    ENTITY read(UUID id);

    List<ENTITY> get();

    ENTITY update(UUID id, Long dtUpdate, IDTO item);


}
