package br.unitins.dto.questionario;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import br.unitins.dto.topico.TopicoResponseDTO;
import br.unitins.model.Questionario;

public record QuestionarioResponseDTO(
    Long id,
    String titulo,
    String descricao,
    List<TopicoResponseDTO> topicos,
    Boolean status,
    Date dataCriacao
) {
    public static QuestionarioResponseDTO valueOf(Questionario questionario) {
        return new QuestionarioResponseDTO(
            questionario.getId(),
            questionario.getTitulo(),
            questionario.getDescricao(),
            questionario.getTopicos().stream()
                .map(topico -> TopicoResponseDTO.valueOf(topico))
                .collect(Collectors.toList()),
            questionario.getStatus(),
            questionario.getDataCriacao()
        );
    }
}

