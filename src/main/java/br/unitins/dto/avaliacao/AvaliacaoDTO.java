package br.unitins.dto.avaliacao;

import java.util.Date;
import java.util.List;

import br.unitins.dto.respostas.RespostasDTO;
import lombok.Getter;

@Getter
public class AvaliacaoDTO {
    Date dataAvaliacao;
    String comentario;
    Double toxicidade;
    boolean visibilidade;
    Long idQuestionario;
    List<RespostasDTO> respostas;
//    Long idAvaliador;
}
