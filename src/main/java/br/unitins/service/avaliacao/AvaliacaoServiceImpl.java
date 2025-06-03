package br.unitins.service.avaliacao;

import java.util.List;
import java.util.stream.Collectors;

import br.unitins.dto.avaliacao.AvaliacaoDTO;
import br.unitins.dto.avaliacao.AvaliacaoResponseDTO;
import br.unitins.dto.questionario.QuestionarioResponseDTO;
import br.unitins.dto.respostas.RespostasResponseDTO;
import br.unitins.model.Avaliacao;
import br.unitins.model.Questionario;
import br.unitins.model.Respostas;
import br.unitins.model.Topico;
import br.unitins.repository.AvaliacaoRepository;
import br.unitins.service.questionario.QuestionarioService;
import br.unitins.service.respostas.RespostasService;
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

    @Inject
    RespostasService respostasService;
   
    @Override
    @Transactional
    public AvaliacaoResponseDTO insert(@Valid AvaliacaoDTO dto) {
        Avaliacao novaAvaliacao = new Avaliacao();
        novaAvaliacao.setDataAvaliacao(dto.getDataAvaliacao());
        novaAvaliacao.setComentario(dto.getComentario());
        novaAvaliacao.setQuestionario(questionarioService.findById(dto.getIdQuestionario()));

        avaliacaoRepository.persist(novaAvaliacao);

         List<Respostas> respostas = dto.getRespostas().stream()
                .map(dtoRespostas -> {
                        dtoRespostas.setIdAvaliacao(novaAvaliacao.getId());
                        RespostasResponseDTO responseDTO = respostasService.insert(dtoRespostas); // Obtém o DTO
                        return convertToEntity(responseDTO);
                })
                .collect(Collectors.toList());

        novaAvaliacao.setRespostas(respostas);

        return AvaliacaoResponseDTO.valueOf(novaAvaliacao);
    }

     private Respostas convertToEntity(RespostasResponseDTO dto) {
        Respostas resposta = new Respostas();
        if (dto.topico() != null) { 
            resposta.setTopico(new Topico()); 
            resposta.getTopico().setNome(dto.topico()); 
        }
        resposta.setEstrela(dto.estrela());
        return resposta;
    }

    @Override
    @Transactional
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
    @Transactional
    public void delete(Long id) {
        if (!avaliacaoRepository.deleteById(id)) 
        throw new NotFoundException("Avaliacao não encontrada com o ID: " + id);
    }

    @Override
    public Avaliacao findById(Long id) {
        return avaliacaoRepository.findById(id);
    }

    @Override
    public List<AvaliacaoResponseDTO> findByNome(String nome, int page, int pageSize) {
        List<Avaliacao> list = avaliacaoRepository.findByNome(nome).page(page, pageSize).list();
        return list.stream()
                .map(e -> AvaliacaoResponseDTO.valueOf(e)).toList();
    }

    @Override
    public List<AvaliacaoResponseDTO> findByAll(int page, int pageSize) {
        List<Avaliacao> list = avaliacaoRepository.findAll().page(page, pageSize).list();
        return list.stream().map(e -> AvaliacaoResponseDTO.valueOf(e)).toList();
    }

    @Override
    public long count() {
        return avaliacaoRepository.count();
    }

    @Override
    public long countByNome(String nome) {
        return avaliacaoRepository.findByNome(nome).count();
    }

}
