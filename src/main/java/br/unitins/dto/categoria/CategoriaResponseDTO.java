package br.unitins.dto.categoria;

import br.unitins.model.Categoria;

public record CategoriaResponseDTO(
    Long id,
    String nome
) {
    public static CategoriaResponseDTO valueOf(Categoria categoria){
        return new CategoriaResponseDTO(
            categoria.getId(),
            categoria.getNome()
        );
    }
}
