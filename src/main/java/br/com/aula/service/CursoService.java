package br.com.aula.service;

import br.com.aula.model.Curso;
import br.com.aula.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursoService {

    @Autowired
    private CursoRepository repository;

    public Curso gravaCurso(Curso curso){
        return repository.save(curso);
    }

    public List<Curso> buscarTudo(){
        return repository.findAll();
    }

    public Optional<Curso> buscaId(Long id_curso){
        return repository.findById(id_curso);
    }

    public void deletaCurso(Optional<Curso> curso){
        repository.delete(curso.get());
    }
}
