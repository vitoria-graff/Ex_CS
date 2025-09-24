package com.example.alunos;

import com.example.alunos.domain.AlunoEntity;
import com.example.alunos.repository.AlunoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class AlunoRepositoryTests {

    @Autowired
    AlunoRepository repository;

    @Test
    void saveAndSearchByNomeContainingIgnoreCase() {
        repository.save(new AlunoEntity("m1","Maria"));
        repository.save(new AlunoEntity("j1","Joao"));

        List<AlunoEntity> result = repository.findByNomeContainingIgnoreCase("ar");
        assertThat(result).extracting(AlunoEntity::getMatricula).contains("m1");
    }
}
