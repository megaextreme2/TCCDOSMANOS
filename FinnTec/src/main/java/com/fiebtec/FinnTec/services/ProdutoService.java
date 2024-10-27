package com.fiebtec.FinnTec.services;


import com.fiebtec.FinnTec.model.Cliente;
import com.fiebtec.FinnTec.model.Produto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProdutoService {
    public Produto salvarProduto(Produto produto);
    public List<Produto> listarTodosProdutos();
    public Produto listarProdutoPorId(Long id);
    public boolean deletarProdutos(Long id);
    public Produto atualizarProdutos(Produto produto, Long id);
    public Produto deletarLogicProdutos(Produto produto, Long id);
    public Produto listarProdutosPorIdAtivos(Long id);
    public List<Produto> listarTodosProdutosAtivos();

}
