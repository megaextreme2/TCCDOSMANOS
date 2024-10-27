package com.fiebtec.FinnTec.controller;

import com.fiebtec.FinnTec.exceptions.BadRequest;
import com.fiebtec.FinnTec.model.Pedido;
import com.fiebtec.FinnTec.services.PedidoService;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins="*", maxAge = 3600, allowCredentials = "false")
@RequestMapping("/pedido")
public class PedidoController {

    // criação do objeto de serviço
    final PedidoService pedidoService;

    // Injeção de Dependência
    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    // ROTA POST
    @PostMapping
    public ResponseEntity<Object> salvarPedido(@RequestBody Pedido pedido){
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoService.salvarPedidos(pedido));
    }

    // ROTA GET
    @GetMapping
    public ResponseEntity<List<Pedido>> listarTodosPedidos(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(pedidoService.listarTodosPedidos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> listarPedidoPorId(@PathVariable(value = "id") String id){
        return ResponseEntity.status(HttpStatus.OK).body(pedidoService.listarPedidosPorId(Long.parseLong(id)));
    }

    @PutMapping("/delete/{id}")
    @Transactional
    public ResponseEntity<Pedido> deletarLogicPedidos(@RequestBody Pedido pedido, @PathVariable(value = "id") String id){

        try {
            return ResponseEntity.ok().body(pedidoService.deletarLogicPedidos(pedido, Long.parseLong(id)));

        }catch (NumberFormatException ex){
            throw new BadRequest("'"+ id + "' Não é um número inteiro válido. Por favor, forneça um valor inteiro, como 10");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizarPedidos(@RequestBody Pedido pedido, @PathVariable(value = "id") String id){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(pedidoService.atualizarPedidos(pedido, Long.parseLong(id)));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletarPedidos(@PathVariable(value = "id") String id){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(pedidoService.deletarPedidos(Long.parseLong(id)));
    }
}
