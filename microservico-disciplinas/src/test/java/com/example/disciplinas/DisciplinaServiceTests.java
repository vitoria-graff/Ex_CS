package com.example.disciplinas;

import com.example.disciplinas.domain.DisciplinaEntity;
import com.example.disciplinas.repository.DisciplinaRepository;
import com.example.disciplinas.service.DisciplinaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class DisciplinaServiceTests {

    private DisciplinaRepository repository;
    private DisciplinaService service;

    @BeforeEach
    void setup() {
        repository = Mockito.mock(DisciplinaRepository.class);
        service = new DisciplinaService(repository);
    }

    @Test
    void searchPriorizaCodigoEHorario() {
        when(repository.findByCodigoAndHorario("COMP101","A")).thenReturn(List.of(new DisciplinaEntity("COMP101","Algoritmos","A")));
        assertThat(service.search(null,"COMP101","A")).hasSize(1);
    }
}
