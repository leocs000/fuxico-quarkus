package br.unitins.service.questionario;

import java.util.List;

import br.unitins.dto.questionario.QuestionarioDTO;
import br.unitins.dto.questionario.QuestionarioResponseDTO;
import br.unitins.model.Questionario;
import jakarta.validation.Valid;

public interface QuestionarioService {
    public QuestionarioResponseDTO insert(@Valid QuestionarioDTO dto);

    public QuestionarioResponseDTO update(QuestionarioDTO dto, Long id);

    public void delete(Long id);

    public QuestionarioResponseDTO findById(Long id);

    public Questionario findEntityById(Long id);
    
    public List<QuestionarioResponseDTO> findByNome(String nome, int page, int pageSize);

    public List<QuestionarioResponseDTO> findByAll(int page, int pageSize);

    public long count();

    public long countByNome(String nome);
}
