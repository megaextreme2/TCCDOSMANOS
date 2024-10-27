package com.fiebtec.FinnTec.repository;

import com.fiebtec.FinnTec.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido,Long> {
    @Query(value = "SELECT * FROM Barbeiro ba WHERE ba.codStatus='1'", nativeQuery = true)
    public List<Pedido> listarTodosPedidosAtivos();

    @Query(value = "SELECT * FROM Barbeiro ba WHERE ba.id=?1 AND ba.codStatus='1'", nativeQuery = true)
    public Pedido listarPedidosPorIdAtivos(Long id);
}
