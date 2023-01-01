package by.it_akademy.fitness.storage.api;

import by.it_akademy.fitness.storage.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IProfileStorage extends JpaRepository<Profile, UUID> {
}
