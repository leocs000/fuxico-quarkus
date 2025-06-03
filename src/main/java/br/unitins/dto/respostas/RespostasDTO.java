package br.unitins.dto.respostas;

import lombok.Getter;

@Getter
public class RespostasDTO {
    Long idAvaliacao;
    Long idTopico;
    Double estrela;

    public void setIdAvaliacao(Long idAvaliacao) {
        this.idAvaliacao = idAvaliacao;
    }
}
