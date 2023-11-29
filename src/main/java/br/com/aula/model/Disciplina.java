package br.com.aula.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "DISCIPLINA")
@JsonIgnoreProperties({"curso"})
public class Disciplina implements Serializable {
    @Id
    @Column(name = "ID_DISCIPLINA")
    private Integer id_disciplina;

    @ManyToOne
    @JoinColumn(name = "ID_CURSO")
    @JsonBackReference
    private Curso curso;

    @Column(name = "NM_DISCIPLINA")
    private String nm_disciplina;

    @Column(name = "NM_PROFESSOR")
    private String nm_professor;

    @ManyToOne
    @JoinColumn(name = "NOTA")
    private Nota nota;

    public Disciplina() {
    }

    public Integer getId_disciplina() {
        return id_disciplina;
    }

    public void setId_disciplina(Integer id_disciplina) {
        this.id_disciplina = id_disciplina;
    }

    public String getNm_disciplina() {
        return nm_disciplina;
    }

    public void setNm_disciplina(String nm_disciplina) {
        this.nm_disciplina = nm_disciplina;
    }

    public String getNm_professor() {
        return nm_professor;
    }

    public void setNm_professor(String nm_professor) {
        this.nm_professor = nm_professor;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Nota getNota() {
        return nota;
    }

    public void setNota(Nota nota) {
        this.nota = nota;
    }
}
