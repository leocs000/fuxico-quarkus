package br.unitins.dto.avaliacao;

import java.util.Date;

import br.unitins.dto.questionario.QuestionarioResponseDTO;
import br.unitins.model.Avaliacao;

public record AvaliacaoResponseDTO(
    Long id,
    Date dataAvaliacao,
    String comentario,
    QuestionarioResponseDTO questionario
) {
    public static AvaliacaoResponseDTO valueOf(Avaliacao avaliacao){
        return new AvaliacaoResponseDTO(
            avaliacao.getId(),
            avaliacao.getDataAvaliacao(), 
            avaliacao.getComentario(), 
            QuestionarioResponseDTO.valueOf(avaliacao.getQuestionario()));
    }
} 
