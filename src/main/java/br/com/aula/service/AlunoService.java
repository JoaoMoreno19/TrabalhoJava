package br.com.aula.service;

import br.com.aula.model.Aluno;
import br.com.aula.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository repository;

    public Aluno gravaAluno(Aluno aluno){
        return repository.save(aluno);
    }

    public List<Aluno> buscarTudo(){
        return repository.findAll();
    }

    public Optional<Aluno> buscaId(Integer id_aluno){
        return repository.findById(id_aluno);
    }

    public void deletaAluno(Optional<Aluno> aluno){
        repository.delete(aluno.get());
    }
}
