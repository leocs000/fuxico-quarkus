package br.unitins.service.respostas;

import java.util.List;

import br.unitins.repository.RespostasRepository;
import br.unitins.dto.respostas.RespostasDTO;
import br.unitins.dto.respostas.RespostasResponseDTO;
import br.unitins.model.Respostas;
import br.unitins.service.avaliacao.AvaliacaoService;
import br.unitins.service.topico.TopicoService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class RespostasServiceImpl implements RespostasService{

    @Inject
    RespostasRepository respostasRepository;

    @Inject
    TopicoService topicoService;

    @Inject
    AvaliacaoService avaliacaoService;

    @Override
    @Transactional
    public RespostasResponseDTO insert(@Valid RespostasDTO dto) {
        Respostas novoResposta = new Respostas();
        novoResposta.setAvaliacao(avaliacaoService.findById(dto.getIdAvaliacao()));
        novoResposta.setTopico(topicoService.findById(dto.getIdTopico()));;
        novoResposta.setEstrela(dto.getEstrela());

        respostasRepository.persist(novoResposta);

        return RespostasResponseDTO.valueOf(novoResposta);
    }

    @Override
    @Transactional
    public RespostasResponseDTO update(RespostasDTO dto, Long id) {
        Respostas respostas = respostasRepository.findById(id);
        if(respostas != null){
            respostas.setAvaliacao(avaliacaoService.findById(dto.getIdAvaliacao()));
            respostas.setTopico(topicoService.findById(dto.getIdTopico()));;
            respostas.setEstrela(dto.getEstrela());
        }else
            throw new NotFoundException();

        return RespostasResponseDTO.valueOf(respostas);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!respostasRepository.deleteById(id)) 
        throw new NotFoundException("Respostas não encontrada com o ID: " + id);
    }

    @Override
    public Respostas findById(Long id) {
        return respostasRepository.findById(id);
    }

    @Override
    public List<RespostasResponseDTO> findByNome(String nome, int page, int pageSize) {
        List<Respostas> list = respostasRepository.findByNome(nome).page(page, pageSize).list();
        return list.stream()
                .map(e -> RespostasResponseDTO.valueOf(e)).toList();
    }

    @Override
    public List<RespostasResponseDTO> findByAll(int page, int pageSize) {
        List<Respostas> list = respostasRepository.findAll().page(page, pageSize).list();
        return list.stream().map(e -> RespostasResponseDTO.valueOf(e)).toList();
    }

    @Override
    public long count() {
        return respostasRepository.count();
    }

    @Override
    public long countByNome(String nome) {
        return respostasRepository.findByNome(nome).count();
    }

}
