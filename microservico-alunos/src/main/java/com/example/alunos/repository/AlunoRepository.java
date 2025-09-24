package com.example.alunos.repository;

import com.example.alunos.domain.AlunoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlunoRepository extends JpaRepository<AlunoEntity, String> {
    List<AlunoEntity> findByNomeContainingIgnoreCase(String nome);
}
