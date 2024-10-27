package com.fiebtec.FinnTec.services;

import com.fiebtec.FinnTec.exceptions.BadRequest;
import com.fiebtec.FinnTec.exceptions.NotFound;
import com.fiebtec.FinnTec.model.Pedido;
import com.fiebtec.FinnTec.repository.PedidoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoServiceImpl implements PedidoService {
    private final PedidoRepository pedidoRepository;

    public PedidoServiceImpl(PedidoRepository pedidoRepository ) {
        this.pedidoRepository = pedidoRepository;
    }


    @Override
    public Pedido salvarPedidos(Pedido pedido) {
        if (!pedido.validarPedido()) {
            pedido.setCod_status(true);
            throw new BadRequest(pedido.getMensagemErro());
        }
        return pedidoRepository.save(pedido);
    }

    @Override
    public List<Pedido> listarTodosPedidos() {
        return pedidoRepository.findAll();
    }

    @Override
    public Pedido listarPedidosPorId(Long id) {
        try {
            return pedidoRepository.findById(id).get();
        } catch (Exception ex) {
            throw new NotFound("Cliente com o id " + id + " não encontrado");
        }
    }

    @Override
    public boolean deletarPedidos(Long id) {
        if (pedidoRepository.existsById(id)) {
            pedidoRepository.deleteById(id);
        } else {
            throw new NotFound("Cliente com o id " + id + " não encontrado");
        }
        return true;
    }

    @Override
    public Pedido atualizarPedidos(Pedido pedido, Long id) {
        try {
            if (!pedido.validarPedido()) {
                throw new BadRequest(pedido.getMensagemErro());
            }
            Pedido pedidoBD = pedidoRepository.findById(id).get();
            pedidoBD.setQtd_produtos(pedido.getQtd_produtos());
            pedidoBD.setPreco_total(pedido.getPreco_total());
            pedidoBD.setCod_status(true);
            return pedidoRepository.save(pedidoBD); // save: dupla função - update para objeto existente (quando não tenho objeto, ele salva, e quando tem, ele atualiza)

        } catch (Exception ex) {
            throw new NotFound("Cliente com o id " + id + " não encontrado");
        }
    }

    @Override
    public Pedido deletarLogicPedidos(Pedido pedido, Long id) {
        try {
            if (!pedido.validarPedido()){
                throw new BadRequest(pedido.getMensagemErro());
            }
            Pedido pedidoBD = pedidoRepository.findById(id).get();
            pedidoBD.setCod_status(pedido.isCod_status());
            return pedidoRepository.save(pedidoBD);
        }catch (Exception ex){
            throw new NotFound("Cliente com o id " + id + " não encontrado");
        }
    }

    @Override
    public Pedido listarPedidosPorIdAtivos(Long id) {
        Pedido pedido = pedidoRepository.listarPedidosPorIdAtivos(id);
        if(pedido == null){
            throw new NotFound("Cliente com o id " + id + " não encontrado");
        }
        return pedido;
    }

    @Override
    public List<Pedido> listarTodosPedidosAtivos() {
        return pedidoRepository.listarTodosPedidosAtivos();
    }
}

