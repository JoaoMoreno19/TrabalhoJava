package br.com.aula.controller;

import br.com.aula.model.Aluno;
import br.com.aula.model.Nota;
import br.com.aula.service.AlunoService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/resultado")
public class ResultadoController {
    @Autowired
    private AlunoService alunoService;

    @GetMapping("/{idAluno}")
    public ResponseEntity<Object> obterResultado(@PathVariable(value = "idAluno") Integer idAluno) {
        Optional<Aluno> alunoOptional = alunoService.buscaId(idAluno);

        if (alunoOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aluno não encontrado!");
        }

        Aluno aluno = alunoOptional.get();
        List<Nota> notas = aluno.getNotas();
        Double media = notas.stream().mapToDouble(Nota::getNota).average().orElse(0.0);

        String status = "";

        if (media >= 7 && media <= 10) {
            status = "Aprovado";
        } else if (media >= 0 && media < 7) {
            status = "Reprovado";
        } else {
            status = "ativo";
        }

        Map<String, Object> resultado = new HashMap<>();
        resultado.put("Nome aluno", aluno.getNm_aluno());
        resultado.put("Cursos", aluno.getCursos());
        resultado.put("media", media);
        resultado.put("status Geral", status);

        return ResponseEntity.status(HttpStatus.OK).body(resultado);
    }
}
