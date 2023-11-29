package br.com.aula.service;

import br.com.aula.model.Nota;
import br.com.aula.repository.NotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotaService {

    @Autowired
    private NotaRepository repository;

    public Nota gravaNota(Nota nota){
        return repository.save(nota);
    }

    public List<Nota> buscarTudo(){
        return repository.findAll();
    }

    public Optional<Nota> buscaId(Integer id_disciplina){
        return repository.findById(id_disciplina);
    }

    public void deletaNota(Optional<Nota> id_disciplina){
        repository.delete(id_disciplina.get());
    }


}
