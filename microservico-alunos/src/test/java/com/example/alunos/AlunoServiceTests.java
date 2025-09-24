package com.example.alunos;

import com.example.alunos.domain.AlunoEntity;
import com.example.alunos.repository.AlunoRepository;
import com.example.alunos.service.AlunoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class AlunoServiceTests {

    private AlunoRepository repository;
    private AlunoService service;

    @BeforeEach
    void setup() {
        repository = Mockito.mock(AlunoRepository.class);
        service = new AlunoService(repository);
    }

    @Test
    void createAluno() {
        AlunoEntity toSave = new AlunoEntity("123","Maria");
        when(repository.save(any())).thenReturn(toSave);
        AlunoEntity saved = service.create(toSave);
        assertThat(saved.getMatricula()).isEqualTo("123");
        assertThat(saved.getNome()).isEqualTo("Maria");
    }

    @Test
    void findByMatricula() {
        when(repository.findById("123")).thenReturn(Optional.of(new AlunoEntity("123","Joao")));
        assertThat(service.findByMatricula("123").orElseThrow().getNome()).isEqualTo("Joao");
    }

    @Test
    void searchByNome() {
        when(repository.findByNomeContainingIgnoreCase("ma")).thenReturn(List.of(new AlunoEntity("1","Maria")));
        assertThat(service.searchByNome("ma")).hasSize(1);
    }
}
