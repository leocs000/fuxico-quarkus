package br.unitins.service.topico;

import java.util.List;

import br.unitins.dto.topico.TopicoDTO;
import br.unitins.dto.topico.TopicoResponseDTO;
import br.unitins.model.Topico;
import br.unitins.repository.TopicoRepository;
import br.unitins.service.questionario.QuestionarioService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class TopicoServiceImpl implements TopicoService{

    @Inject
    TopicoRepository topicoRepository;

    @Inject
    QuestionarioService questionarioService;

    @Override
    @Transactional
    public TopicoResponseDTO insert(@Valid TopicoDTO dto) {
        Topico novoTopico = new Topico();
        novoTopico.setNome(dto.getNome());
        novoTopico.setDescricao(dto.getDescricao());
        novoTopico.setEstrelas(dto.getEstrelas());
        novoTopico.setQuestionario(questionarioService.findById(dto.getIdQuestionario()));

        topicoRepository.persist(novoTopico);

        return TopicoResponseDTO.valueOf(novoTopico);
    }

    @Override
    @Transactional
    public TopicoResponseDTO update(TopicoDTO dto, Long id) {
        Topico Topico = topicoRepository.findById(id);
        if(Topico != null){
            Topico.setNome(dto.getNome());
            Topico.setQuestionario(questionarioService.findById(dto.getIdQuestionario()));
        }else
            throw new NotFoundException();

        return TopicoResponseDTO.valueOf(Topico);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!topicoRepository.deleteById(id)) 
        throw new NotFoundException("Topico não encontrada com o ID: " + id);
    }

    @Override
    public Topico findById(Long id) {
        return topicoRepository.findById(id);
    }

    @Override
    public List<TopicoResponseDTO> findByNome(String nome, int page, int pageSize) {
        List<Topico> list = topicoRepository.findByNome(nome).page(page, pageSize).list();
        return list.stream()
                .map(e -> TopicoResponseDTO.valueOf(e)).toList();
    }

    @Override
    public List<TopicoResponseDTO> findByAll(int page, int pageSize) {
        List<Topico> list = topicoRepository.findAll().page(page, pageSize).list();
        return list.stream().map(e -> TopicoResponseDTO.valueOf(e)).toList();
    }

    @Override
    public long count() {
        return topicoRepository.count();
    }

    @Override
    public long countByNome(String nome) {
        return topicoRepository.findByNome(nome).count();
    }

}
