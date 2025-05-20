package br.unitins.dto.subcategoria;

import br.unitins.dto.categoria.CategoriaDTO;
import br.unitins.model.SubCategoria;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class SubCategoriaDTO{
    String nome;
    Long idCategoria;
} 
