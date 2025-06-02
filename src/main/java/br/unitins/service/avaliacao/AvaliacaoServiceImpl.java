package br.unitins.service.avaliacao;

import java.util.List;

import br.unitins.dto.avaliacao.AvaliacaoDTO;
import br.unitins.dto.avaliacao.AvaliacaoResponseDTO;
import br.unitins.dto.subcategoria.SubCategoriaResponseDTO;
import br.unitins.model.Avaliacao;
import br.unitins.model.SubCategoria;
import br.unitins.repository.AvaliacaoRepository;
import br.unitins.service.questionario.QuestionarioService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class AvaliacaoServiceImpl implements AvaliacaoService{

    @Inject
    AvaliacaoRepository avaliacaoRepository;

    @Inject
    QuestionarioService questionarioService;
   
    @Override
    @Transactional
    public AvaliacaoResponseDTO insert(@Valid AvaliacaoDTO dto) {
        Avaliacao novaAvaliacao = new Avaliacao();
        novaAvaliacao.setDataAvaliacao(dto.getDataAvaliacao());
        novaAvaliacao.setComentario(dto.getComentario());
        novaAvaliacao.setQuestionario(questionarioService.findById(dto.getIdQuestionario()));

        avaliacaoRepository.persist(novaAvaliacao);

        return AvaliacaoResponseDTO.valueOf(novaAvaliacao);
    }

    @Override
    public AvaliacaoResponseDTO update(AvaliacaoDTO dto, Long id) {
        Avaliacao avaliacao = new Avaliacao();
        if(avaliacao != null){
            avaliacao.setDataAvaliacao(dto.getDataAvaliacao());
            avaliacao.setComentario(dto.getComentario());
            avaliacao.setQuestionario(questionarioService.findById(dto.getIdQuestionario()));
        }else
            throw new NotFoundException();
        
        return AvaliacaoResponseDTO.valueOf(avaliacao);
    }

    @Override
    public void delete(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public Avaliacao findById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public List<AvaliacaoResponseDTO> findByNome(String nome, int page, int pageSize) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByNome'");
    }

    @Override
    public List<AvaliacaoResponseDTO> findByAll(int page, int pageSize) {
        List<Avaliacao> list = avaliacaoRepository.findAll().page(page, pageSize).list();
        return list.stream().map(e -> AvaliacaoResponseDTO.valueOf(e)).toList();
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
