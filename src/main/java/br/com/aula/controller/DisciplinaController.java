package br.com.aula.controller;

import br.com.aula.model.Disciplina;
import br.com.aula.model.Nota;
import br.com.aula.service.DisciplinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/disciplina")
public class DisciplinaController {
    @Autowired
    private DisciplinaService service;

    @PostMapping
    @ResponseBody
    public ResponseEntity<Disciplina> gravarDiscipina(@RequestBody Disciplina disciplina) {
        Disciplina novaDisciplina = service.gravaDisciplina(disciplina);
        return ResponseEntity.status(HttpStatus.OK).body(novaDisciplina);
    }

    @GetMapping
    public ResponseEntity<List<Disciplina>> buscarTudo() {
        return ResponseEntity.status(HttpStatus.OK).body(service.buscarTudo());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Disciplina>> buscarID(@PathVariable(value = "id") Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.buscaId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizaDisciplina(@PathVariable(value = "id") Integer id, @RequestBody Disciplina disciplina) {

        Optional<Disciplina> d = service.buscaId(id);

        if(d.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body("Não localizado!");
        }

        Disciplina disciplina1 = d.get();
        disciplina1.setNm_disciplina(disciplina.getNm_disciplina());
        disciplina1.setNm_professor(disciplina.getNm_professor());

        return ResponseEntity.status(HttpStatus.OK).body(service.gravaDisciplina(disciplina1));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> atualizaDisciplina(@PathVariable(value = "id") Integer id) {

        Optional<Disciplina> d = service.buscaId(id);

        if(d.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body("Não localizado !");
        }

        service.deletaDisciplina(d);

        return ResponseEntity.status(HttpStatus.OK).body("Disciplina Deletada !");

    }
}
