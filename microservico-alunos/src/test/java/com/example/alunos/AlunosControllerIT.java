package com.example.alunos;

import com.example.alunos.domain.AlunoEntity;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AlunosControllerIT {

    @LocalServerPort
    int port;

    TestRestTemplate rest = new TestRestTemplate();

    @Test
    void criarEBuscarAluno() {
        AlunoEntity a = new AlunoEntity("999","Teste");
        ResponseEntity<AlunoEntity> created = rest.postForEntity("http://localhost:"+port+"/alunos", a, AlunoEntity.class);
        assertThat(created.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        ResponseEntity<AlunoEntity> fetched = rest.getForEntity("http://localhost:"+port+"/alunos/999", AlunoEntity.class);
        assertThat(fetched.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(fetched.getBody().getNome()).isEqualTo("Teste");
    }
}
