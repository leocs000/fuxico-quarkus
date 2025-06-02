package br.unitins.dto.respostas;

import br.unitins.dto.avaliacao.AvaliacaoResponseDTO;
import br.unitins.dto.topico.TopicoResponseDTO;
import br.unitins.model.Respostas;

public record RespostasResponseDTO(
    Long id,
    AvaliacaoResponseDTO avaliacao,
    TopicoResponseDTO topico,
    Double estrela
) {
    public static RespostasResponseDTO valueOf(Respostas respostas){
        return new RespostasResponseDTO(
            respostas.getId(), 
            Avanull, null, null)
    }
} 

