package com.fiebtec.FinnTec.services;



import com.fiebtec.FinnTec.model.Funcionario;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FuncionarioService {
    public Funcionario salvarFuncionario(Funcionario barbeiro);
    public List<Funcionario> listarTodosFuncionarios();
    public Funcionario listarFuncionarioPorId(Long id);
    public boolean deletarFuncionario(Long id);
    public Funcionario atualizarFuncionario(Funcionario barbeiro, Long id);
    public Funcionario deletarLogicFuncionario(Funcionario barbeiro, Long id);
    public Funcionario listarFuncionariosPorIdAtivos(Long id);
    public List<Funcionario> listarTodosFuncionariosAtivos();
}
