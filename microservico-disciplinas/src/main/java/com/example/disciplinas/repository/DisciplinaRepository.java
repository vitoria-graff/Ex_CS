package com.example.disciplinas.repository;

import com.example.disciplinas.domain.DisciplinaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DisciplinaRepository extends JpaRepository<DisciplinaEntity, String> {
    List<DisciplinaEntity> findByNomeContainingIgnoreCase(String nome);
    List<DisciplinaEntity> findByCodigo(String codigo);
    List<DisciplinaEntity> findByCodigoAndHorario(String codigo, String horario);
}
