package br.com.aula.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.io.Serializable;

@Entity
@Table(name = "NOTA")
public class Nota implements Serializable {

    @ManyToOne()
    @JoinColumn(name = "NM_DISCIPLINA")
    @JsonBackReference
    private Disciplina disciplina;

    @ManyToOne()
    @JoinColumn(name = "ID_ALUNO")
    @JsonBackReference
    private Aluno aluno;

    @Id
    @Column(name = "NOTA")
    private Double nota;

    private String status;

    public Nota() {
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

    @PrePersist
    public void setStatusAutomatico() {
        if (nota >= 7) {
            status = "Aprovado";
        } else if (nota > 0) {
            status = "Reprovado";
        } else {
            status = "Ativo";
        }
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }
}
