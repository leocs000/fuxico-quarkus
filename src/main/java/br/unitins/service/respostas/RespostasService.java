package br.unitins.service.respostas;

import java.util.List;

import br.unitins.dto.respostas.RespostasDTO;
import br.unitins.dto.respostas.RespostasResponseDTO;
import br.unitins.model.Respostas;
import jakarta.validation.Valid;

public interface RespostasService {
    public RespostasResponseDTO insert(@Valid RespostasDTO dto);

    public RespostasResponseDTO update(RespostasDTO dto, Long id);

    public void delete(Long id);

    public Respostas findById(Long id);
    
    public List<RespostasResponseDTO> findByNome(String nome, int page, int pageSize);

    public List<RespostasResponseDTO> findByAll(int page, int pageSize);

    public long count();

    public long countByNome(String nome);
}
