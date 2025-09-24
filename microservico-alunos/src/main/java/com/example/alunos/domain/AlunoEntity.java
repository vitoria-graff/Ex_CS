package com.example.alunos.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "alunos")
public class AlunoEntity {
    @Id
    @Column(length = 64, nullable = false, unique = true)
    private String matricula;

    @Column(length = 255, nullable = false)
    private String nome;

    public AlunoEntity() {}
    public AlunoEntity(String matricula, String nome) {
        this.matricula = matricula;
        this.nome = nome;
    }
    public String getMatricula() { return matricula; }
    public void setMatricula(String matricula) { this.matricula = matricula; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
}
