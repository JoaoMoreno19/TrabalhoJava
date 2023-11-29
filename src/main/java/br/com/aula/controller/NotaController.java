package br.com.aula.controller;

import br.com.aula.model.Aluno;
import br.com.aula.model.Nota;
import br.com.aula.service.NotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/nota")
public class NotaController {
    @Autowired
    private NotaService service;

    @PostMapping
    public ResponseEntity<Object> gravarNota(@RequestBody Nota nota) {
        if (nota.getNota() >= 7) {
            nota.setStatus("Aprovado");
        } else if (nota.getNota() > 0) {
            nota.setStatus("Reprovado");
        } else {
            nota.setStatus("Ativo");
        }
        return ResponseEntity.status(HttpStatus.OK).body(service.gravaNota(nota));
    }

    @GetMapping
    public ResponseEntity<List<Nota>> buscarTudo() {
        return ResponseEntity.status(HttpStatus.OK).body(service.buscarTudo());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Nota>> buscarID(@PathVariable(value = "id") Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.buscaId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizaAluno(@PathVariable(value = "id") Integer id, @RequestBody Nota nota) {

        Optional<Nota> n = service.buscaId(id);

        if(n.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body("Não localizado!");
        }

        Nota nota1 = n.get();
        nota1.setNota(nota.getNota());
        nota1.setStatus(nota.getStatus());

        return ResponseEntity.status(HttpStatus.OK).body(service.gravaNota(nota1));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> atualizaNota(@PathVariable(value = "id") Integer id) {

        Optional<Nota> n = service.buscaId(id);

        if(n.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body("Não localizado !");
        }

        service.deletaNota(n);

        return ResponseEntity.status(HttpStatus.OK).body("Nota do aluno Deletada !");

    }

}
