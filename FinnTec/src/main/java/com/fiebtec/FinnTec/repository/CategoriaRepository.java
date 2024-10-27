package com.fiebtec.FinnTec.repository;

import com.fiebtec.FinnTec.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoriaRepository extends JpaRepository<Categoria,Long> {
    @Query(value = "SELECT * FROM Barbeiro ba WHERE ba.codStatus='1'", nativeQuery = true)
    public List<Categoria> listarTodosCategoriasAtivos();

    @Query(value = "SELECT * FROM Barbeiro ba WHERE ba.id=?1 AND ba.codStatus='1'", nativeQuery = true)
    public Categoria listarCategoriasPorIdAtivos(Long id);
}
