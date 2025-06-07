package br.unitins.service.tipoAvaliador;

import java.util.List;

import br.unitins.dto.tipoAvaliador.TipoAvaliadorDTO;
import br.unitins.dto.tipoAvaliador.TipoAvaliadorResponseDTO;
import br.unitins.model.TipoAvaliador;
import br.unitins.repository.TipoAvaliadorRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class TipoAvaliadorServiceImpl implements TipoAvaliadorService{

    @Inject
    TipoAvaliadorRepository tipoAvaliadorRepository;

    @Override
    @Transactional
    public TipoAvaliadorResponseDTO insert(@Valid TipoAvaliadorDTO dto) {
        TipoAvaliador novoTipoAvaliador = new TipoAvaliador();
        novoTipoAvaliador.setDescricao(dto.getDescricao());

        tipoAvaliadorRepository.persist(novoTipoAvaliador);

        return TipoAvaliadorResponseDTO.valueOf(novoTipoAvaliador);
    }

    @Override
    @Transactional
    public TipoAvaliadorResponseDTO update(TipoAvaliadorDTO dto, Long id) {
        TipoAvaliador tipoAvaliador = tipoAvaliadorRepository.findById(id);
        if(tipoAvaliador != null){
            tipoAvaliador.setDescricao(dto.getDescricao());
        }else
            throw new NotFoundException();

        return TipoAvaliadorResponseDTO.valueOf(tipoAvaliador);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!tipoAvaliadorRepository.deleteById(id)) 
        throw new NotFoundException("TipoAvaliador não encontrada com o ID: " + id);
    }

    @Override
    public TipoAvaliador findById(Long id) {
        return tipoAvaliadorRepository.findById(id);
    }

    @Override
    public List<TipoAvaliadorResponseDTO> findByNome(String nome, int page, int pageSize) {
        List<TipoAvaliador> list = tipoAvaliadorRepository.findByNome(nome).page(page, pageSize).list();
        return list.stream()
                .map(e -> TipoAvaliadorResponseDTO.valueOf(e)).toList();
    }

    @Override
    public List<TipoAvaliadorResponseDTO> findByAll(int page, int pageSize) {
        List<TipoAvaliador> list = tipoAvaliadorRepository.findAll().page(page, pageSize).list();
        return list.stream().map(e -> TipoAvaliadorResponseDTO.valueOf(e)).toList();
    }

    @Override
    public long count() {
        return tipoAvaliadorRepository.count();
    }

    @Override
    public long countByNome(String nome) {
        return tipoAvaliadorRepository.findByNome(nome).count();
    }

}
