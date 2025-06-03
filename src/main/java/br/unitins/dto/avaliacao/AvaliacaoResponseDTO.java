package br.unitins.dto.avaliacao;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import br.unitins.dto.questionario.QuestionarioResponseDTO;
import br.unitins.dto.respostas.RespostasResponseDTO;
import br.unitins.model.Avaliacao;

public record AvaliacaoResponseDTO(
    Long id,
    Date dataAvaliacao,
    String comentario,
    Double toxicidade,
    boolean visibilidade,
    Long idQuestionario,
    String tituloQuestionario,
    String descricaoQuestionario,
    List<RespostasResponseDTO> respostas
) {
    public static AvaliacaoResponseDTO valueOf(Avaliacao avaliacao){
        return new AvaliacaoResponseDTO(
            avaliacao.getId(),
            avaliacao.getDataAvaliacao(), 
            avaliacao.getComentario(), 
            avaliacao.getToxicidade(),
            avaliacao.isVisibiliadade(),
            avaliacao.getQuestionario().getId(),
            avaliacao.getQuestionario().getTitulo(),
            avaliacao.getQuestionario().getDescricao(),
            avaliacao.getRespostas().stream()
                .map(resposta -> RespostasResponseDTO.valueOf(resposta))
                .collect(Collectors.toList()));
    }
} 
