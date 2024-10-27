package com.fiebtec.FinnTec.repository;

import com.fiebtec.FinnTec.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FuncionarioRepository extends JpaRepository<Funcionario,Long> {
    @Query(value = "SELECT * FROM Barbeiro ba WHERE ba.codStatus='1'", nativeQuery = true)
    public List<Funcionario> listarTodosFuncionariosAtivos();

    @Query(value = "SELECT * FROM Barbeiro ba WHERE ba.id=?1 AND ba.codStatus='1'", nativeQuery = true)
    public Funcionario listarFuncionariosPorIdAtivos(Long id);

}
