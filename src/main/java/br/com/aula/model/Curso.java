package br.com.aula.model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "CURSO")
@JsonIgnoreProperties("aluno")
public class Curso implements Serializable {
    @Id
    @Column(name = "ID_CURSO")
    private Integer id_curso;

    @ManyToOne
    @JoinColumn(name = "ID_ALUNO")
    @JsonBackReference
    private Aluno aluno;

    @OneToMany(mappedBy = "curso", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Disciplina> disciplinas;

    @Column(name = "NM_CURSO")
    private String nome_curso;

    @Column(name = "QTD_HORAS")
    private Double qtd_horas;

    public Curso() {
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Integer getId_curso() {
        return id_curso;
    }

    public void setId_curso(Integer id_curso) {
        this.id_curso = id_curso;
    }

    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(List<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }

    public String getNome_curso() {
        return nome_curso;
    }

    public void setNome_curso(String nome_curso) {
        this.nome_curso = nome_curso;
    }

    public Double getQtd_horas() {
        return qtd_horas;
    }

    public void setQtd_horas(Double qtd_horas) {
        this.qtd_horas = qtd_horas;
    }


}
