package br.unitins.service.categoria;

import java.util.List;

import br.unitins.dto.categoria.CategoriaDTO;
import br.unitins.dto.categoria.CategoriaResponseDTO;
import br.unitins.model.Categoria;
import br.unitins.repository.CategoriaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class CategoriaServiceImpl implements CategoriaService{

    @Inject
    CategoriaRepository repository;

    @Override
    public CategoriaResponseDTO insert(@Valid CategoriaDTO dto) {
        Categoria novaCategoria = new Categoria();
        novaCategoria.setNome(dto.nome());
        repository.persist(novaCategoria);
        return CategoriaResponseDTO.valueOf(novaCategoria);
    }

    @Override
    @Transactional
    public CategoriaResponseDTO update(CategoriaDTO dto, Long id) {
        Categoria categoria = repository.findById(id);
        if (categoria != null) {
            categoria.setNome(dto.nome());
           
        } else
            throw new NotFoundException();

        return CategoriaResponseDTO.valueOf(categoria);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!repository.deleteById(id))
            throw new NotFoundException();
    }

    @Override
    public Categoria findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<CategoriaResponseDTO> findByNome(String nome, int page, int pageSize) {
        List<Categoria> list = repository.findByNome(nome).page(page, pageSize).list();
        return list.stream()
                .map(e -> CategoriaResponseDTO.valueOf(e)).toList();
    }

    @Override
    public List<CategoriaResponseDTO> findByAll(int page, int pageSize) {
        List<Categoria> list = repository.findAll().page(page, pageSize).list();
        return list.stream().map(e -> CategoriaResponseDTO.valueOf(e)).toList();
    }

    @Override
    public long count() {
        return repository.count();
    }

    @Override
    public long countByNome(String nome) {
        return repository.findByNome(nome).count();
    }

}
