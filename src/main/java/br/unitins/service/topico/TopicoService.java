package br.unitins.service.topico;

import java.util.List;

import br.unitins.dto.topico.TopicoDTO;
import br.unitins.dto.topico.TopicoResponseDTO;
import br.unitins.model.Topico;
import jakarta.validation.Valid;

public interface TopicoService {
    public TopicoResponseDTO insert(@Valid TopicoDTO dto);

    public TopicoResponseDTO update(TopicoDTO dto, Long id);

    public void delete(Long id);

    public Topico findById(Long id);
    
    public List<TopicoResponseDTO> findByNome(String nome, int page, int pageSize);

    public List<TopicoResponseDTO> findByAll(int page, int pageSize);

    public long count();

    public long countByNome(String nome);
}
