package com.example.disciplinas;

import com.example.disciplinas.api.DisciplinasController;
import com.example.disciplinas.domain.DisciplinaEntity;
import com.example.disciplinas.service.DisciplinaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = DisciplinasController.class)
class DisciplinasControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private DisciplinaService service;

    @Test
    void create_returns201() throws Exception {
        DisciplinaEntity d = new DisciplinaEntity("COMP101","Algoritmos","A");
        when(service.create(any(DisciplinaEntity.class))).thenReturn(d);

        mockMvc.perform(post("/disciplinas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(d)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.codigo", is("COMP101")));
    }

    @Test
    void list_byCodigoHorario() throws Exception {
        when(service.search(eq(null), eq("COMP101"), eq("A")))
                .thenReturn(List.of(new DisciplinaEntity("COMP101","Algoritmos","A")));

        mockMvc.perform(get("/disciplinas").param("codigo", "COMP101").param("horario", "A"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(1))));
    }
}
