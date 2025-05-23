package br.unitins.dto.topico;

import br.unitins.model.Topico;

public record TopicoResponseDTO(
    Long id,
    String nome,
    String descricao,
    Double estrelas
){
    public static TopicoResponseDTO valueOf(Topico topico){
        return new TopicoResponseDTO(
            topico.getId(),
            topico.getNome(),
            topico.getDescricao(),
            topico.getEstrelas()
        );
    }
}
