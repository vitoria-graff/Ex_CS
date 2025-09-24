package com.example.disciplinas;

import com.example.disciplinas.domain.DisciplinaEntity;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DisciplinasControllerIT {
    @LocalServerPort
    int port;

    TestRestTemplate rest = new TestRestTemplate();

    @Test
    void criarEListarPorCodigoHorario() {
        DisciplinaEntity d = new DisciplinaEntity("COMP101","Algoritmos","A");
        ResponseEntity<DisciplinaEntity> created = rest.postForEntity("http://localhost:"+port+"/disciplinas", d, DisciplinaEntity.class);
        assertThat(created.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        ResponseEntity<DisciplinaEntity[]> list = rest.getForEntity("http://localhost:"+port+"/disciplinas?codigo=COMP101&horario=A", DisciplinaEntity[].class);
        assertThat(list.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(list.getBody()).isNotNull();
        assertThat(list.getBody().length).isGreaterThanOrEqualTo(1);
    }
}
