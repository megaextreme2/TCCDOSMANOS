package com.fiebtec.FinnTec.controller;

import com.fiebtec.FinnTec.exceptions.BadRequest;
import com.fiebtec.FinnTec.model.Funcionario;
import com.fiebtec.FinnTec.services.FuncionarioService;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins="*", maxAge = 3600, allowCredentials = "false")
@RequestMapping("/funcionario")
public class FuncionarioController {

    // criação do objeto de serviço
    final FuncionarioService funcionarioService;

    // Injeção de Dependência
    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    // ROTA POST
    @PostMapping
    public ResponseEntity<Object> salvarFuncionario(@RequestBody Funcionario funcionario){
        return ResponseEntity.status(HttpStatus.CREATED).body(funcionarioService.salvarFuncionario(funcionario));
    }

    // ROTA GET
    @GetMapping
    public ResponseEntity<List<Funcionario>> listarTodosFuncionarios(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(funcionarioService.listarTodosFuncionarios());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Funcionario> listarFuncionarioPorId(@PathVariable(value = "id") String id){
        return ResponseEntity.status(HttpStatus.OK).body(funcionarioService.listarFuncionarioPorId(Long.parseLong(id)));
    }

    @PutMapping("/delete/{id}")
    @Transactional
    public ResponseEntity<Funcionario> deletarLogicFuncionario(@RequestBody Funcionario funcionario, @PathVariable(value = "id") String id){

        try {
            return ResponseEntity.ok().body(funcionarioService.deletarLogicFuncionario(funcionario, Long.parseLong(id)));

        }catch (NumberFormatException ex){
            throw new BadRequest("'"+ id + "' Não é um número inteiro válido. Por favor, forneça um valor inteiro, como 10");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizarFuncionario(@RequestBody Funcionario funcionario, @PathVariable(value = "id") String id){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(funcionarioService.atualizarFuncionario(funcionario, Long.parseLong(id)));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletarFuncionario(@PathVariable(value = "id") String id){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(funcionarioService.deletarFuncionario(Long.parseLong(id)));
    }
}
