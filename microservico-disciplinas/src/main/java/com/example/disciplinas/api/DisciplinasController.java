package com.example.disciplinas.api;

import com.example.disciplinas.domain.DisciplinaEntity;
import com.example.disciplinas.service.DisciplinaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/disciplinas")
public class DisciplinasController {
    private final DisciplinaService service;

    public DisciplinasController(DisciplinaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<DisciplinaEntity> create(@RequestBody @Valid DisciplinaEntity input) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(input));
    }

    @GetMapping
    public ResponseEntity<List<DisciplinaEntity>> list(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String codigo,
            @RequestParam(required = false) String horario
    ) {
        return ResponseEntity.ok(service.search(nome, codigo, horario));
    }
}
