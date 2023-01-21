package by.it_akademy.fitness.service.api;

import by.it_akademy.fitness.exception.LockException;

import java.util.List;
import java.util.UUID;

public interface IService<ENTITY, IDTO, ODTO> {

    ENTITY create(IDTO dto,String header);

    ENTITY read(UUID id);

    List<ENTITY> get();

    ENTITY update(UUID id, Long dtUpdate, IDTO item,String header) throws LockException;

    void delete(ENTITY entity);


}
