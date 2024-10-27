package com.fiebtec.FinnTec.services;

import com.fiebtec.FinnTec.exceptions.BadRequest;
import com.fiebtec.FinnTec.exceptions.NotFound;
import com.fiebtec.FinnTec.model.Categoria;
import com.fiebtec.FinnTec.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategoriaServiceImpl implements CategoriaService {
    private final CategoriaRepository categoriaRepository;

    public CategoriaServiceImpl(CategoriaRepository categoriaRepository ) {
        this.categoriaRepository = categoriaRepository;
    }


    @Override
    public Categoria salvarCategoria(Categoria categoria) {
        if (!categoria.validarCategoria()) {
            categoria.setCod_status(true);
            throw new BadRequest(categoria.getMensagemErro());
        }
        return categoriaRepository.save(categoria);
    }

    @Override
    public List<Categoria> listarTodasCategorias() {
        return categoriaRepository.findAll();
    }

    @Override
    public Categoria listarCategoriaPorId(Long id) {
        try {
            return categoriaRepository.findById(id).get();
        } catch (Exception ex) {
            throw new NotFound("Categoria com o id " + id + " não encontrado");
        }
    }

    @Override
    public boolean deletarCategorias(Long id) {
        if (categoriaRepository.existsById(id)) {
            categoriaRepository.deleteById(id);
        } else {
            throw new NotFound("Categoria com o id " + id + " não encontrado");
        }
        return true;
    }

    @Override
    public Categoria atualizarCategorias(Categoria categoria, Long id) {
        try {
            if (!categoria.validarCategoria()) {
                throw new BadRequest(categoria.getMensagemErro());
            }
            Categoria categoriaBD = categoriaRepository.findById(id).get();
            categoriaBD.setNome(categoria.getNome());
            categoriaBD.setDescricao(categoria.getDescricao());
            categoriaBD.setCod_status(true);
            return categoriaRepository.save(categoriaBD); // save: dupla função - update para objeto existente (quando não tenho objeto, ele salva, e quando tem, ele atualiza)

        } catch (Exception ex) {
            throw new NotFound("Categoria com o id " + id + " não encontrado");
        }
    }

    @Override
    public Categoria deletarLogicCategoria(Categoria categoria, Long id) {
        try {
            if (!categoria.validarCategoria()){
                throw new BadRequest(categoria.getMensagemErro());
            }
            Categoria categoriaBD = categoriaRepository.findById(id).get();
            categoriaBD.setCod_status(categoriaBD.isCod_status());
            return categoriaRepository.save(categoriaBD);
        }catch (Exception ex){
            throw new NotFound("Categoria com o id " + id + " não encontrado");
        }
    }

    @Override
    public Categoria listarCategoriasPorIdAtivos(Long id) {
        Categoria categoria = categoriaRepository.listarCategoriasPorIdAtivos(id);
        if(categoria == null){
            throw new NotFound("Categoria com o id " + id + " não encontrado");
        }
        return categoria;
    }

    @Override
    public List<Categoria> listarTodosCategoriasAtivos() {
        return categoriaRepository.listarTodosCategoriasAtivos();
    }
}

