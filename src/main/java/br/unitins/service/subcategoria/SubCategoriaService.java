package br.unitins.service.subcategoria;

import java.util.List;

import br.unitins.dto.subcategoria.SubCategoriaDTO;
import br.unitins.dto.subcategoria.SubCategoriaResponseDTO;
import br.unitins.model.SubCategoria;
import jakarta.validation.Valid;

public interface SubCategoriaService {
    public SubCategoriaResponseDTO insert(@Valid SubCategoriaDTO dto);

    public SubCategoriaResponseDTO update(SubCategoriaDTO dto, Long id);

    public void delete(Long id);

    public SubCategoria findById(Long id);
    
    public List<SubCategoriaResponseDTO> findByNome(String nome, int page, int pageSize);

    public List<SubCategoriaResponseDTO> findByAll(int page, int pageSize);

    public long count();

    public long countByNome(String nome);
}
