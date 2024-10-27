package com.fiebtec.FinnTec.services;


import com.fiebtec.FinnTec.model.Cliente;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClienteService {
    public Cliente salvarClientes(Cliente cliente);
    public List<Cliente> listarTodosClientes();
    public Cliente listarClientesPorId(Long id);
    public boolean deletarClientes(Long id);
    public Cliente atualizarClientes(Cliente cliente, Long id);
    public Cliente deletarLogicClientes(Cliente cliente, Long id);
    public Cliente listarClientesPorIdAtivos(Long id);
    public List<Cliente> listarTodosClientesAtivos();

}
