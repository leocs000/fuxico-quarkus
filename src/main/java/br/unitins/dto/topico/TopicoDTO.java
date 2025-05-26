package br.unitins.dto.topico;

import lombok.Getter;

@Getter
public class TopicoDTO {
    Long id;
    String nome;
    String descricao;
    Double estrelas;
    Long idQuestionario;

    public void setIdQuestionario(Long idQuestionario) {
        this.idQuestionario = idQuestionario;
    }
}
