package br.unitins.service.avaliador;

import java.util.List;

import br.unitins.dto.avaliador.AvaliadorDTO;
import br.unitins.dto.avaliador.AvaliadorResponseDTO;
import br.unitins.model.Avaliador;
import jakarta.validation.Valid;

public interface AvaliadorService {
    public AvaliadorResponseDTO insert(@Valid AvaliadorDTO dto);

    public AvaliadorResponseDTO update(AvaliadorDTO dto, Long id);

    public void delete(Long id);

    public AvaliadorResponseDTO findById(Long id);

    public AvaliadorResponseDTO findByUsuario(String login);

    public List<AvaliadorResponseDTO> findByNome(String nome, int page, int pageSize);

    public List<AvaliadorResponseDTO> findByAll(int page, int pageSize); 

    public long count();

    public long countByNome(String nome);
}
