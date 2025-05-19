package br.unitins.dto.categoria;

import br.unitins.model.Categoria;
import jakarta.validation.constraints.NotBlank;

public record CategoriaDTO (
        @NotBlank(message = "Insira o nome da categoria corretamente")
        String nome 
    ){
        public static CategoriaDTO valueOf(Categoria categoria){
            return new CategoriaDTO(
                categoria.getNome());
        }
    }
