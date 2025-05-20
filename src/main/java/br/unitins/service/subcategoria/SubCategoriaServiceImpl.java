package br.unitins.service.subcategoria;

import java.util.List;

import br.unitins.dto.subcategoria.SubCategoriaDTO;
import br.unitins.dto.subcategoria.SubCategoriaResponseDTO;
import br.unitins.model.SubCategoria;
import br.unitins.repository.SubCategoriaRepository;
import br.unitins.service.categoria.CategoriaService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class SubCategoriaServiceImpl implements SubCategoriaService{

    @Inject
    SubCategoriaRepository subCategoriaRepository;

    @Inject
    CategoriaService categoriaService;

    @Override
    @Transactional
    public SubCategoriaResponseDTO insert(@Valid SubCategoriaDTO dto) {
        SubCategoria novaSubCategoria = new SubCategoria();
        novaSubCategoria.setNome(dto.getNome());
        novaSubCategoria.setCategoria(categoriaService.findById(dto.getIdCategoria()));

        subCategoriaRepository.persist(novaSubCategoria);

        return SubCategoriaResponseDTO.valueOf(novaSubCategoria);
    }

    @Override
    @Transactional
    public SubCategoriaResponseDTO update(SubCategoriaDTO dto, Long id) {
        SubCategoria subCategoria = subCategoriaRepository.findById(id);
        if(subCategoria != null){
            subCategoria.setNome(dto.getNome());
            subCategoria.setCategoria(categoriaService.findById(dto.getIdCategoria()));
        }else
            throw new NotFoundException();

        return SubCategoriaResponseDTO.valueOf(subCategoria);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!subCategoriaRepository.deleteById(id)) 
        throw new NotFoundException("Subcategoria não encontrada com o ID: " + id);
    }

    @Override
    public SubCategoria findById(Long id) {
        return subCategoriaRepository.findById(id);
    }

    @Override
    public List<SubCategoriaResponseDTO> findByNome(String nome, int page, int pageSize) {
        List<SubCategoria> list = subCategoriaRepository.findByNome(nome).page(page, pageSize).list();
        return list.stream()
                .map(e -> SubCategoriaResponseDTO.valueOf(e)).toList();
    }

    @Override
    public List<SubCategoriaResponseDTO> findByAll(int page, int pageSize) {
        List<SubCategoria> list = subCategoriaRepository.findAll().page(page, pageSize).list();
        return list.stream().map(e -> SubCategoriaResponseDTO.valueOf(e)).toList();
    }

    @Override
    public long count() {
        return subCategoriaRepository.count();
    }

    @Override
    public long countByNome(String nome) {
        return subCategoriaRepository.findByNome(nome).count();
    }

}
