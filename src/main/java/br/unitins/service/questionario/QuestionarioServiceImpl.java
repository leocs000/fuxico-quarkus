package br.unitins.service.questionario;

import java.util.List;
import java.util.stream.Collectors;

import br.unitins.dto.questionario.QuestionarioDTO;
import br.unitins.dto.questionario.QuestionarioResponseDTO;
import br.unitins.dto.topico.TopicoDTO;
import br.unitins.model.Questionario;
import br.unitins.model.Topico;
import br.unitins.repository.QuestionarioRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class QuestionarioServiceImpl implements QuestionarioService{

    @Inject
    QuestionarioRepository questionarioRepository;

    @Override
    public QuestionarioResponseDTO insert(QuestionarioDTO dto) {

        Questionario novoQuestionario = new Questionario();
            novoQuestionario.setTitulo(dto.getTitulo());
            novoQuestionario.setDescricao(dto.getDescricao());
            novoQuestionario.setStatus(dto.getStatus());
            novoQuestionario.setDataCriacao(dto.getDataCriacao());

            List<Topico> topicos = dto.getTopicos().stream()
                .map(this::convertToEntity)
            .collect(Collectors.toList());

            novoQuestionario.setTopicos(topicos);

            questionarioRepository.persist(novoQuestionario);

            return QuestionarioResponseDTO.valueOf(novoQuestionario);
    }

    private Topico convertToEntity(TopicoDTO dto) {
        Topico topico = new Topico();
        topico.setNome(dto.getNome());
        topico.setDescricao(dto.getDescricao());
        topico.setEstrelas(dto.getEstrelas());
        return topico;
    }


    @Override
    public QuestionarioResponseDTO update(QuestionarioDTO dto, Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public Questionario findById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public List<QuestionarioResponseDTO> findByNome(String nome, int page, int pageSize) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByNome'");
    }

    @Override
    public List<QuestionarioResponseDTO> findByAll(int page, int pageSize) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByAll'");
    }

    @Override
    public long count() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'count'");
    }

    @Override
    public long countByNome(String nome) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'countByNome'");
    }

}
