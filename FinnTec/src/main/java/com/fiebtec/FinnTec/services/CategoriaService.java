package com.fiebtec.FinnTec.services;


import com.fiebtec.FinnTec.model.Categoria;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoriaService {
    public Categoria salvarCategoria(Categoria categoria);
    public List<Categoria> listarTodasCategorias();
    public Categoria listarCategoriaPorId(Long id);
    public boolean deletarCategorias(Long id);
    public Categoria atualizarCategorias(Categoria categoria, Long id);
    public Categoria deletarLogicCategoria(Categoria categoria, Long id);
    public Categoria listarCategoriasPorIdAtivos(Long id);
    public List<Categoria> listarTodosCategoriasAtivos();

}
