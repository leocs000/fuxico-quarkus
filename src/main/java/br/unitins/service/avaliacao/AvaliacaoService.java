package br.unitins.service.avaliacao;

import java.util.List;

import br.unitins.dto.avaliacao.AvaliacaoDTO;
import br.unitins.dto.avaliacao.AvaliacaoResponseDTO;
import br.unitins.model.Avaliacao;
import jakarta.validation.Valid;

public interface AvaliacaoService {
    public AvaliacaoResponseDTO insert(@Valid AvaliacaoDTO dto);

    public AvaliacaoResponseDTO update(AvaliacaoDTO dto, Long id);

    public void delete(Long id);

    public Avaliacao findById(Long id);
    
    public List<AvaliacaoResponseDTO> findByNome(String nome, int page, int pageSize);

    public List<AvaliacaoResponseDTO> findByAll(int page, int pageSize);

    public long count();

    public long countByNome(String nome);

    public List<AvaliacaoResponseDTO> listarAvaliacoesPendentes(int page, int pageSize);

    public AvaliacaoResponseDTO aprovarAvaliacao(Long id);

}