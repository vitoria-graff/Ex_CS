package com.example.alunos.service;

import com.example.alunos.domain.AlunoEntity;
import com.example.alunos.repository.AlunoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {

    private final AlunoRepository repository;

    public AlunoService(AlunoRepository repository) {
        this.repository = repository;
    }

    public AlunoEntity create(AlunoEntity aluno) {
        return repository.save(aluno);
    }

    public Optional<AlunoEntity> findByMatricula(String matricula) {
        return repository.findById(matricula);
    }

    public List<AlunoEntity> searchByNome(String nome) {
        if (nome == null || nome.isBlank()) {
            return repository.findAll();
        }
        return repository.findByNomeContainingIgnoreCase(nome);
    }
}
