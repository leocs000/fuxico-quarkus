package br.unitins.service.questionario;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import br.unitins.dto.questionario.QuestionarioDTO;
import br.unitins.dto.questionario.QuestionarioResponseDTO;
import br.unitins.dto.topico.TopicoDTO;
import br.unitins.dto.topico.TopicoResponseDTO;
import br.unitins.model.Questionario;
import br.unitins.model.Topico;
import br.unitins.repository.QuestionarioRepository;
import br.unitins.service.subcategoria.SubCategoriaService;
import br.unitins.service.topico.TopicoService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class QuestionarioServiceImpl implements QuestionarioService{

    @Inject
    QuestionarioRepository questionarioRepository;

    @Inject
    TopicoService topicoService;

    @Inject
    SubCategoriaService subCategoriaService;

    @Override
    public QuestionarioResponseDTO insert(QuestionarioDTO dto) {

        Questionario novoQuestionario = new Questionario();
            novoQuestionario.setTitulo(dto.getTitulo());
            novoQuestionario.setDescricao(dto.getDescricao());
            novoQuestionario.setStatus(dto.getStatus());
            novoQuestionario.setDataCriacao(dto.getDataCriacao());
            novoQuestionario.setSubcategoria(subCategoriaService.findById(dto.getIdSubcategoria()));

            questionarioRepository.persist(novoQuestionario);

            List<Topico> topicos = dto.getTopicos().stream()
                .map(dtoTopico -> {
                        dtoTopico.setIdQuestionario(novoQuestionario.getId());
                        TopicoResponseDTO responseDTO = topicoService.insert(dtoTopico); // Obtém o DTO
                        return convertToEntity(responseDTO);//new Topico(responseDTO.id(), responseDTO.nome(), responseDTO.descricao(), responseDTO.estrelas()); // Converte para entidade
                })
                .collect(Collectors.toList());


            novoQuestionario.setTopicos(topicos);

        return QuestionarioResponseDTO.valueOf(novoQuestionario);
    }

    private Topico convertToEntity(TopicoResponseDTO dto) {
        Topico topico = new Topico();
        topico.setNome(dto.nome());
        topico.setDescricao(dto.descricao());
        topico.setEstrelas(dto.estrelas());
        return topico;
    }


    @Override
    public QuestionarioResponseDTO update(QuestionarioDTO dto, Long id) {
        Questionario questionario = questionarioRepository.findById(id);
        if (questionario == null)
            throw new NotFoundException();

        questionario.setTitulo(dto.getTitulo());
        questionario.setDescricao(dto.getDescricao());
        questionario.setStatus(dto.getStatus());
        questionario.setDataCriacao(dto.getDataCriacao());
        questionario.setSubcategoria(subCategoriaService.findById(dto.getIdSubcategoria()));

        List<Long> novosIds = dto.getTopicos().stream()
            .map(TopicoDTO::getId)
            .filter(Objects::nonNull)
            .toList();

        List<Topico> topicosParaRemover = questionario.getTopicos().stream()
            .filter(t -> !novosIds.contains(t.getId()))
            .toList();

        // Remove os tópicos que foram excluídos visualmente
        topicosParaRemover.forEach(topico -> {
            topicoService.delete(topico.getId());
        });

        // Atualiza e adiciona os tópicos restantes
        List<Topico> topicosAtualizados = dto.getTopicos().stream()
            .map(dtoTopico -> {
                TopicoResponseDTO responseDTO =
                    dtoTopico.getId() == null
                        ? topicoService.insert(dtoTopico)
                        : topicoService.update(dtoTopico, dtoTopico.getId());
                return convertToEntity(responseDTO);
            })
            .toList();

        questionario.setTopicos(topicosAtualizados);

        return QuestionarioResponseDTO.valueOf(questionario);
    }


    @Override
    @Transactional
    public void delete(Long id) {
        if (!questionarioRepository.deleteById(id)) 
        throw new NotFoundException("Questionario não encontrada com o ID: " + id);
    }

    @Override
    public QuestionarioResponseDTO findById(Long id) {
        return QuestionarioResponseDTO.valueOf(questionarioRepository.findById(id));
    }

    @Override
    public Questionario findEntityById(Long id) {
        return questionarioRepository.findById(id);
    }

    @Override
    public List<QuestionarioResponseDTO> findByNome(String nome, int page, int pageSize) {
        List<Questionario> list = questionarioRepository.findByNome(nome).page(page, pageSize).list();
        return list.stream()
                .map(e -> QuestionarioResponseDTO.valueOf(e)).toList();
    }

    @Override
    public List<QuestionarioResponseDTO> findByAll(int page, int pageSize) {
        List<Questionario> list = questionarioRepository.findAll().page(page, pageSize).list();
        return list.stream().map(e -> QuestionarioResponseDTO.valueOf(e)).toList();
    }

    @Override
    public long count() {
        return questionarioRepository.count();
    }

    @Override
    public long countByNome(String nome) {
        return questionarioRepository.findByNome(nome).count();
    }

}
