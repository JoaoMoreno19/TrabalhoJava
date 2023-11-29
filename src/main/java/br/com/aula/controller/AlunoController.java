package br.com.aula.controller;

import br.com.aula.model.Aluno;
import br.com.aula.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/aluno")
public class AlunoController {
    @Autowired
    private AlunoService service;

    @PostMapping
    @ResponseBody
    public ResponseEntity<Aluno> gravarAluno(@RequestBody Aluno aluno) {
        Aluno novoAluno = service.gravaAluno(aluno);
        return ResponseEntity.status(HttpStatus.OK).body(novoAluno);
    }

    @GetMapping
    public ResponseEntity<List<Aluno>> buscarTudo() {
        return ResponseEntity.status(HttpStatus.OK).body(service.buscarTudo());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Aluno>> buscarID(@PathVariable(value = "id") Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.buscaId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizaAluno(@PathVariable(value = "id") Integer id, @RequestBody Aluno aluno) {

        Optional<Aluno> a = service.buscaId(id);

        if(a.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body("Não localizado!");
        }

        Aluno aluno1 = a.get();
        aluno1.setNm_aluno(aluno.getNm_aluno());
        aluno1.setDt_nascimento(aluno.getDt_nascimento());
        aluno1.setCpf(aluno.getCpf());
        aluno1.setRg(aluno.getRg());

        return ResponseEntity.status(HttpStatus.OK).body(service.gravaAluno(aluno1));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> atualizaAluno(@PathVariable(value = "id") Integer id) {

        Optional<Aluno> a = service.buscaId(id);

        if(a.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body("Não localizado !");
        }

        service.deletaAluno(a);

        return ResponseEntity.status(HttpStatus.OK).body("Aluno Deletado !");

    }
}
