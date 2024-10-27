package com.fiebtec.FinnTec.services;

import com.fiebtec.FinnTec.exceptions.BadRequest;
import com.fiebtec.FinnTec.exceptions.NotFound;
import com.fiebtec.FinnTec.model.Cliente;
import com.fiebtec.FinnTec.model.Produto;
import com.fiebtec.FinnTec.repository.ClienteRepository;
import com.fiebtec.FinnTec.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoServiceImpl implements ProdutoService {
    private final ProdutoRepository produtoRepository;

    public ProdutoServiceImpl(ProdutoRepository produtoRepository ) {
        this.produtoRepository = produtoRepository;
    }


    @Override
    public Produto salvarProduto(Produto produto) {
        if (!produto.validarProduto()) {
            produto.setCod_status(true);
            throw new BadRequest(produto.getMensagemErro());
        }
        return produtoRepository.save(produto);
    }

    @Override
    public List<Produto> listarTodosProdutos() {
        return produtoRepository.findAll();
    }

    @Override
    public Produto listarProdutoPorId(Long id) {
        try {
            return produtoRepository.findById(id).get();
        } catch (Exception ex) {
            throw new NotFound("Produto com o id " + id + " não encontrado");
        }
    }

    @Override
    public boolean deletarProdutos(Long id) {
        if (produtoRepository.existsById(id)) {
            produtoRepository.deleteById(id);
        } else {
            throw new NotFound("Produto com o id " + id + " não encontrado");
        }
        return true;
    }

    @Override
    public Produto atualizarProdutos(Produto produto, Long id) {
        try {
            if (!produto.validarProduto()) {
                throw new BadRequest(produto.getMensagemErro());
            }
            Produto produtoBD = produtoRepository.findById(id).get();
            produtoBD.setNome(produto.getNome());
            produtoBD.setDescricao(produto.getDescricao());
            produtoBD.setQtd_estoque(produto.getQtd_estoque());
            produtoBD.setPreco(produto.getPreco());
            produtoBD.setCod_status(true);
            return produtoRepository.save(produtoBD); // save: dupla função - update para objeto existente (quando não tenho objeto, ele salva, e quando tem, ele atualiza)

        } catch (Exception ex) {
            throw new NotFound("Produto com o id " + id + " não encontrado");
        }
    }

    @Override
    public Produto deletarLogicProdutos(Produto produto, Long id) {
        try {
            if (!produto.validarProduto()){
                throw new BadRequest(produto.getMensagemErro());
            }
            Produto produtoBD = produtoRepository.findById(id).get();
            produtoBD.setCod_status(produto.isCod_status());
            return produtoRepository.save(produtoBD);
        }catch (Exception ex){
            throw new NotFound("Produto com o id " + id + " não encontrado");
        }
    }

    @Override
    public Produto listarProdutosPorIdAtivos(Long id) {
        Produto produto = produtoRepository.listarProdutosPorIdAtivos(id);
        if(produto == null){
            throw new NotFound("Produto com o id " + id + " não encontrado");
        }
        return produto;
    }

    @Override
    public List<Produto> listarTodosProdutosAtivos() {
        return produtoRepository.listarTodosProdutosAtivos();
    }
}

