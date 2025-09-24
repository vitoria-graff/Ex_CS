package com.example.alunos.api;

import com.example.alunos.domain.AlunoEntity;
import com.example.alunos.service.AlunoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunosController {

    private final AlunoService service;

    public AlunosController(AlunoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<AlunoEntity> create(@RequestBody @Valid AlunoEntity input) {
        AlunoEntity saved = service.create(input);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping("/{matricula}")
    public ResponseEntity<AlunoEntity> getByMatricula(@PathVariable String matricula) {
        return service.findByMatricula(matricula)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<AlunoEntity>> search(@RequestParam(name = "nome", required = false) String nome) {
        return ResponseEntity.ok(service.searchByNome(nome));
    }
}
