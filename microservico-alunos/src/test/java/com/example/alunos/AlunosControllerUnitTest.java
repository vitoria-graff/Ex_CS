package com.example.alunos;

import com.example.alunos.api.AlunosController;
import com.example.alunos.domain.AlunoEntity;
import com.example.alunos.service.AlunoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = AlunosController.class)
class AlunosControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AlunoService alunoService;

    @Test
    void createAluno_returns201() throws Exception {
        AlunoEntity input = new AlunoEntity("123", "Maria");
        when(alunoService.create(any(AlunoEntity.class))).thenReturn(input);

        mockMvc.perform(post("/alunos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.matricula", is("123")))
                .andExpect(jsonPath("$.nome", is("Maria")));
    }

    @Test
    void getByMatricula_foundAndNotFound() throws Exception {
        when(alunoService.findByMatricula("1")).thenReturn(Optional.of(new AlunoEntity("1", "Joao")));
        when(alunoService.findByMatricula("404")).thenReturn(Optional.empty());

        mockMvc.perform(get("/alunos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome", is("Joao")));

        mockMvc.perform(get("/alunos/404"))
                .andExpect(status().isNotFound());
    }

    @Test
    void searchByNome_returnsList() throws Exception {
        when(alunoService.searchByNome(eq("ma")))
                .thenReturn(List.of(new AlunoEntity("m1", "Maria")));

        mockMvc.perform(get("/alunos").param("nome", "ma"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(1))))
                .andExpect(jsonPath("$[0].matricula", is("m1")));
    }
}
