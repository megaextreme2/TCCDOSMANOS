package com.fiebtec.FinnTec.services;


import com.fiebtec.FinnTec.model.Cliente;
import com.fiebtec.FinnTec.model.Pedido;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PedidoService {
    public Pedido salvarPedidos(Pedido pedido);
    public List<Pedido> listarTodosPedidos();
    public Pedido listarPedidosPorId(Long id);
    public boolean deletarPedidos(Long id);
    public Pedido atualizarPedidos(Pedido pedido, Long id);
    public Pedido deletarLogicPedidos(Pedido pedido, Long id);
    public Pedido listarPedidosPorIdAtivos(Long id);
    public List<Pedido> listarTodosPedidosAtivos();

}
