package by.it_akademy.fitness.storage.api;

import by.it_akademy.fitness.storage.entity.DiaryFood;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface IDiaryFoodStorage extends JpaRepository<DiaryFood, UUID> {
}
