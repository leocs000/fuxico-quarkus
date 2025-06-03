package br.unitins.dto.respostas;

import br.unitins.model.Respostas;

public record RespostasResponseDTO(
    Long id,
    String topico,
    Double estrela
) {
    public static RespostasResponseDTO valueOf(Respostas respostas){
        return new RespostasResponseDTO(
            respostas.getId(),
            respostas.getTopico().getNome(), 
            respostas.getEstrela());
    }
} 

