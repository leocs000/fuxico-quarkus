package br.unitins.service.categoria;

import java.util.List;

import br.unitins.dto.categoria.CategoriaDTO;
import br.unitins.dto.categoria.CategoriaResponseDTO;
import br.unitins.model.Categoria;
import jakarta.validation.Valid;

public interface CategoriaService {
    public CategoriaResponseDTO insert(@Valid CategoriaDTO dto);

    public CategoriaResponseDTO update(CategoriaDTO dto, Long id);

    public void delete(Long id);

    public Categoria findById(Long id);
    
    public List<CategoriaResponseDTO> findByNome(String nome, int page, int pageSize);

    public List<CategoriaResponseDTO> findByAll(int page, int pageSize);

    public long count();

    public long countByNome(String nome);
} 

