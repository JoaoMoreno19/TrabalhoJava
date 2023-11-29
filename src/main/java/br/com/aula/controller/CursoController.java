package br.com.aula.controller;

import br.com.aula.model.Curso;
import br.com.aula.model.Disciplina;
import br.com.aula.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/curso")
public class CursoController {
    @Autowired
    private CursoService service;

    @PostMapping
    @ResponseBody
    public ResponseEntity<Curso> gravarCurso(@RequestBody Curso curso) {
        Curso novoCurso = service.gravaCurso(curso);
        return ResponseEntity.status(HttpStatus.OK).body(novoCurso);
    }

    @GetMapping
    public ResponseEntity<List<Curso>> buscarTudo() {
        return ResponseEntity.status(HttpStatus.OK).body(service.buscarTudo());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Curso>> buscarID(@PathVariable(value = "id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.buscaId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizaCurso(@PathVariable(value = "id") Long id, @RequestBody Curso curso) {

        Optional<Curso> c = service.buscaId(id);

        if(c.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body("Não localizado!");
        }

        Curso curso1 = c.get();
        curso1.setNome_curso(curso.getNome_curso());
        curso1.setQtd_horas(curso.getQtd_horas());

        return ResponseEntity.status(HttpStatus.OK).body(service.gravaCurso(curso1));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> atualizaCurso(@PathVariable(value = "id") Long id) {

        Optional<Curso> c = service.buscaId(id);

        if(c.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body("Não localizado !");
        }

        service.deletaCurso(c);

        return ResponseEntity.status(HttpStatus.OK).body("Curso Deletado !");

    }
}
