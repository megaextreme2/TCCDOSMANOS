package com.fiebtec.FinnTec.repository;

import com.fiebtec.FinnTec.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto,Long> {
    @Query(value = "SELECT * FROM Barbeiro ba WHERE ba.codStatus='1'", nativeQuery = true)
    public List<Produto> listarTodosProdutosAtivos();

    @Query(value = "SELECT * FROM Barbeiro ba WHERE ba.id=?1 AND ba.codStatus='1'", nativeQuery = true)
    public Produto listarProdutosPorIdAtivos(Long id);
}
