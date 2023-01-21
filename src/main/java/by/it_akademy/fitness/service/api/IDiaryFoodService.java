package by.it_akademy.fitness.service.api;

import by.it_akademy.fitness.idto.InputDiaryFoodDTO;
import by.it_akademy.fitness.idto.InputProfileDTO;
import by.it_akademy.fitness.odto.OutputDiaryFoodDTO;
import by.it_akademy.fitness.storage.entity.DiaryFood;

import java.util.UUID;


public interface IDiaryFoodService extends IService<DiaryFood, InputDiaryFoodDTO, OutputDiaryFoodDTO>{
    DiaryFood createWithParam(InputDiaryFoodDTO idto , String header, UUID uuid);
}
