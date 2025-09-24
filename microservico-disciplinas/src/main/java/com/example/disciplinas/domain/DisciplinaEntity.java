package com.example.disciplinas.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "disciplinas")
public class DisciplinaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String codigo;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, length = 1)
    private String horario; // A..G

    public DisciplinaEntity() {}

    public DisciplinaEntity(String codigo, String nome, String horario) {
        this.codigo = codigo; this.nome = nome; this.horario = horario;
    }

    public String getId() { return id; }
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getHorario() { return horario; }
    public void setHorario(String horario) { this.horario = horario; }
}
