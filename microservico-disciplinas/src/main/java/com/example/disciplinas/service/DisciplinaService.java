package com.example.disciplinas.service;

import com.example.disciplinas.domain.DisciplinaEntity;
import com.example.disciplinas.repository.DisciplinaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DisciplinaService {
    private final DisciplinaRepository repository;

    public DisciplinaService(DisciplinaRepository repository) {
        this.repository = repository;
    }

    public DisciplinaEntity create(DisciplinaEntity d) { return repository.save(d); }

    public List<DisciplinaEntity> search(String nome, String codigo, String horario) {
        if (codigo != null && horario != null) return repository.findByCodigoAndHorario(codigo, horario);
        if (codigo != null) return repository.findByCodigo(codigo);
        if (nome != null && !nome.isBlank()) return repository.findByNomeContainingIgnoreCase(nome);
        return repository.findAll();
    }
}
