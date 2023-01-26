package by.it_akademy.fitness.storage.api;

import by.it_akademy.fitness.storage.entity.Audit;
import by.it_akademy.fitness.storage.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IAuditStorage extends JpaRepository<Audit,UUID> {

    Optional<Audit> findById(@NonNull UUID id);

   // List<Audit> findAllById(Iterable<UUID> ids);
    Page<Audit> findAll(Pageable pageable);

     List<Audit> findByUid( String id);
}
