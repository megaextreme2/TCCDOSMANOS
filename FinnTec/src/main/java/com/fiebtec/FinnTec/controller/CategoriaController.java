package com.fiebtec.FinnTec.controller;

import com.fiebtec.FinnTec.exceptions.BadRequest;
import com.fiebtec.FinnTec.model.Categoria;
import com.fiebtec.FinnTec.services.CategoriaService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins="*", maxAge = 3600, allowCredentials = "false")
@RequestMapping("/categoria")
public class CategoriaController {

    // criação do objeto de serviço
    private final CategoriaService categoriaService;

    // Injeção de Dependência
    public CategoriaController(CategoriaService categoriaService){
        this.categoriaService = categoriaService;
    }

    // ROTA POST
    @PostMapping
    public ResponseEntity<Object> salvarCategoria(@RequestBody Categoria categoria){
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaService.salvarCategoria(categoria));
    }

    // ROTA GET
    @GetMapping
    public ResponseEntity<List<Categoria>> listarTodosCategorias(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(categoriaService.listarTodasCategorias());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> listarCategoriaPorId(@PathVariable(value = "id") String id){
        return ResponseEntity.status(HttpStatus.OK).body(categoriaService.listarCategoriaPorId(Long.parseLong(id)));
    }

    @PutMapping("/delete/{id}")
    @Transactional
    public ResponseEntity<Categoria> deletarLogicCategoria(@RequestBody Categoria categoria, @PathVariable(value = "id") String id){

        try {
            return ResponseEntity.ok().body(categoriaService.deletarLogicCategoria(categoria, Long.parseLong(id)));

        }catch (NumberFormatException ex){
            throw new BadRequest("'"+ id + "' Não é um número inteiro válido. Por favor, forneça um valor inteiro, como 10");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizarCategoria(@RequestBody Categoria categoria, @PathVariable(value = "id") String id){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(categoriaService.atualizarCategorias(categoria, Long.parseLong(id)));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletarCategoria(@PathVariable(value = "id") String id){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(categoriaService.deletarCategorias(Long.parseLong(id)));
    }
}
