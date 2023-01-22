package by.it_akademy.fitness.controller;

import by.it_akademy.fitness.service.api.IAuditService;
import by.it_akademy.fitness.storage.entity.Audit;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class AuditServlet {

    public final IAuditService service;

    @GetMapping("/audit")
    public ResponseEntity<? extends List<Audit>> getPage () {
        return ResponseEntity.ok(service.get());
    }
    @GetMapping(path = "/audit", params = "uuid")
    public ResponseEntity<? extends List<Audit>> getUserById(@RequestParam(name = "uuid") String uuid) {
        return ResponseEntity.ok(service.getById(uuid));

    }
}
