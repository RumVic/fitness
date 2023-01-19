package by.it_akademy.fitness.service.api;

import by.it_akademy.fitness.idto.InputUserDTO;
import by.it_akademy.fitness.odto.OutputUserDTO;
import by.it_akademy.fitness.storage.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface IUserService extends IService<User, InputUserDTO, OutputUserDTO> {

    UserDetails createNewUser(InputUserDTO dto);

    UserDetails loadUserByLogin(String login);

}

