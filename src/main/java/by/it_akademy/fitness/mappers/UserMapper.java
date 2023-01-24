package by.it_akademy.fitness.mappers;

import by.it_akademy.fitness.odto.OutputUserDTO;
import by.it_akademy.fitness.storage.entity.User;

public class UserMapper {

    public OutputUserDTO onceMap(User user){

        OutputUserDTO dto = new OutputUserDTO();
        dto.setId(user.getId());
        dto.setDtCrate(user.getDtCrate());
        dto.setDtUpdate(user.getDtUpdate());
        dto.setLogin(user.getLogin());
        dto.setRole(user.getRole());
        dto.setStatus(user.getStatus());

        return dto;
    }

}
