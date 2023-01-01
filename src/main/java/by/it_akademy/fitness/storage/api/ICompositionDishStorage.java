package by.it_akademy.fitness.storage.api;

import by.it_akademy.fitness.storage.entity.CompositionDish;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface ICompositionDishStorage extends JpaRepository<CompositionDish, UUID> {
}
