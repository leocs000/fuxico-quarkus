package br.unitins.service.tipoAvaliador;

import java.util.List;

import br.unitins.dto.tipoAvaliador.TipoAvaliadorDTO;
import br.unitins.dto.tipoAvaliador.TipoAvaliadorResponseDTO;
import br.unitins.model.TipoAvaliador;
import jakarta.validation.Valid;

public interface TipoAvaliadorService {
    public TipoAvaliadorResponseDTO insert(@Valid TipoAvaliadorDTO dto);

    public TipoAvaliadorResponseDTO update(TipoAvaliadorDTO dto, Long id);

    public void delete(Long id);

    public TipoAvaliador findById(Long id);
    
    public List<TipoAvaliadorResponseDTO> findByNome(String nome, int page, int pageSize);

    public List<TipoAvaliadorResponseDTO> findByAll(int page, int pageSize);

    public long count();

    public long countByNome(String nome);
}
