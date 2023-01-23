package by.it_akademy.fitness.controller;

import by.it_akademy.fitness.service.api.IAuditService;
import by.it_akademy.fitness.storage.entity.Audit;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class AuditServlet {

    public final IAuditService service;

    @GetMapping("/audit")
    public ResponseEntity<? extends List<Audit>> getPage () {
        return ResponseEntity.ok(service.get());
    }

    @GetMapping("/audit/{uuid}")
    public ResponseEntity<? extends List<Audit>> getUserById(@PathVariable(name = "uuid") String uuid) {
        return ResponseEntity.ok(service.getById(uuid));

    }
}
