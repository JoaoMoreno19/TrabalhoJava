package br.com.aula.service;

import br.com.aula.model.Disciplina;
import br.com.aula.repository.DisciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DisciplinaService {
    @Autowired
    private DisciplinaRepository repository;

    public Disciplina gravaDisciplina(Disciplina disciplina){
        return repository.save(disciplina);
    }

    public List<Disciplina> buscarTudo(){
        return repository.findAll();
    }

    public Optional<Disciplina> buscaId(Integer id_disciplina){
        return repository.findById(id_disciplina);
    }

    public void deletaDisciplina(Optional<Disciplina> disciplina){
        repository.delete(disciplina.get());
    }
}
