package com.example.disciplinas;

import com.example.disciplinas.domain.DisciplinaEntity;
import com.example.disciplinas.repository.DisciplinaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class DisciplinaRepositoryTests {

    @Autowired
    DisciplinaRepository repository;

    @Test
    void saveAndQueryByCodigoAndHorario() {
        repository.save(new DisciplinaEntity("COMP101","Algoritmos","A"));
        repository.save(new DisciplinaEntity("COMP101","Algoritmos","B"));
        repository.save(new DisciplinaEntity("COMP202","Estruturas","A"));

        List<DisciplinaEntity> result = repository.findByCodigoAndHorario("COMP101","A");
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getHorario()).isEqualTo("A");
    }
}
