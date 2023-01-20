package by.it_akademy.fitness.service.api;

import by.it_akademy.fitness.idto.InputProfileDTO;
import by.it_akademy.fitness.odto.OutputProfileDTO;
import by.it_akademy.fitness.storage.entity.Profile;

public interface IProfileService extends IService<Profile, InputProfileDTO, OutputProfileDTO>{
}
