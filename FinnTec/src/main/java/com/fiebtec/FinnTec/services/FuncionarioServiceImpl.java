package com.fiebtec.FinnTec.services;



import com.fiebtec.FinnTec.exceptions.BadRequest;
import com.fiebtec.FinnTec.exceptions.NotFound;
import com.fiebtec.FinnTec.model.Funcionario;
import com.fiebtec.FinnTec.repository.FuncionarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class FuncionarioServiceImpl implements FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;

    public FuncionarioServiceImpl(FuncionarioRepository funcionarioRepository ) {
        this.funcionarioRepository = funcionarioRepository;
    }

    @Override
    @Transactional
    public Funcionario salvarFuncionario(Funcionario funcionario) {
        if (!funcionario.validarFuncionario()) {
            funcionario.setCod_status(true);
            throw new BadRequest(funcionario.getMensagemErro());
        }
        return funcionarioRepository.save(funcionario);
    }

    @Override
    public List<Funcionario> listarTodosFuncionarios() {
        return funcionarioRepository.findAll();
    }

    @Override
    public Funcionario listarFuncionarioPorId(Long id) {
        try {
            return funcionarioRepository.findById(id).get();
        } catch (Exception ex) {
            throw new NotFound("Funcionario com o id " + id + " não encontrado");
        }
    }

    @Override
    public boolean deletarFuncionario(Long id) {
        if (funcionarioRepository.existsById(id)) {
            funcionarioRepository.deleteById(id);
        } else {
            throw new NotFound("Funcionario com o id " + id + " não encontrado");
        }
        return true;
    }

    @Override
    public Funcionario atualizarFuncionario(Funcionario funcionario, Long id) {
        try {
            if (!funcionario.validarFuncionario()) {
                Funcionario funcionarioBD = funcionarioRepository.findById(id).get();
                funcionarioBD.setNome(funcionario.getNome());
                funcionarioBD.setCod_status(true);
                return funcionarioRepository.save(funcionarioBD);
            } else {
                throw new BadRequest(funcionario.getMensagemErro());
            }
        }catch (Exception ex){
            throw new NotFound("Funcionario com o id " + id + " não encontrado");
        }
    }

    @Override
    public Funcionario deletarLogicFuncionario(Funcionario funcionario, Long id) {
        try {
            if (!funcionario.validarFuncionario()){
                throw new BadRequest(funcionario.getMensagemErro());
            }
            Funcionario funcionarioBD = funcionarioRepository.findById(id).get();
            funcionarioBD.setCod_status(funcionario.isCod_status());
            return funcionarioRepository.save(funcionarioBD);
        }catch (Exception ex){
            throw new NotFound("Funcionario com o id " + id + " não encontrado");
        }
    }

    @Override
    public Funcionario listarFuncionariosPorIdAtivos(Long id) {
        Funcionario funcionario = funcionarioRepository.listarFuncionariosPorIdAtivos(id);
            if(funcionario == null){
                throw new NotFound("Funcionario com o id " + id + " não encontrado");
            }
            return funcionario;
        }
    @Override
    public List<Funcionario> listarTodosFuncionariosAtivos() {
        return funcionarioRepository.listarTodosFuncionariosAtivos();
    }


}
