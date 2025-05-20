package br.unitins.dto.subcategoria;

import br.unitins.dto.categoria.CategoriaResponseDTO;
import br.unitins.model.SubCategoria;

public record SubCategoriaResponseDTO(
    Long id,
    String nome,
    CategoriaResponseDTO categoria
) {
    public static SubCategoriaResponseDTO valueOf(SubCategoria subCategoria){
        return new SubCategoriaResponseDTO(
            subCategoria.getId(),
            subCategoria.getNome(),
            CategoriaResponseDTO.valueOf(subCategoria.getCategoria())
        );
    }
}
