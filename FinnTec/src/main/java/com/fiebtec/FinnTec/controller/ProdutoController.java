package com.fiebtec.FinnTec.controller;

import com.fiebtec.FinnTec.exceptions.BadRequest;
import com.fiebtec.FinnTec.model.Produto;
import com.fiebtec.FinnTec.services.ProdutoService;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins="*", maxAge = 3600, allowCredentials = "false")
@RequestMapping("/produto")
public class ProdutoController {

    // criação do objeto de serviço
    final ProdutoService produtoService;

    // Injeção de Dependência
    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    // ROTA POST
    @PostMapping
    public ResponseEntity<Object> salvarProduto(@RequestBody Produto produto){
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoService.salvarProduto(produto));
    }

    // ROTA GET
    @GetMapping
    public ResponseEntity<List<Produto>> listarTodosProdutos(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(produtoService.listarTodosProdutos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> listarProdutoPorId(@PathVariable(value = "id") String id){
        return ResponseEntity.status(HttpStatus.OK).body(produtoService.listarProdutoPorId(Long.parseLong(id)));
    }

    @PutMapping("/delete/{id}")
    @Transactional
    public ResponseEntity<Produto> deletarLogicProdutos(@RequestBody Produto produto, @PathVariable(value = "id") String id){

        try {
            return ResponseEntity.ok().body(produtoService.deletarLogicProdutos(produto, Long.parseLong(id)));

        }catch (NumberFormatException ex){
            throw new BadRequest("'"+ id + "' Não é um número inteiro válido. Por favor, forneça um valor inteiro, como 10");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizarProdutos(@RequestBody Produto produto, @PathVariable(value = "id") String id){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(produtoService.atualizarProdutos(produto, Long.parseLong(id)));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletarProdutos(@PathVariable(value = "id") String id){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(produtoService.deletarProdutos(Long.parseLong(id)));
    }
}
