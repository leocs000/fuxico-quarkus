package br.unitins.dto.tipoAvaliador;

import br.unitins.model.TipoAvaliador;

public record TipoAvaliadorResponseDTO(
    Long id,
    String descricao
) {
    public static TipoAvaliadorResponseDTO valueOf(TipoAvaliador tipoAvaliador){
        return new TipoAvaliadorResponseDTO(
            tipoAvaliador.getId(),
            tipoAvaliador.getDescricao());
    }
} 