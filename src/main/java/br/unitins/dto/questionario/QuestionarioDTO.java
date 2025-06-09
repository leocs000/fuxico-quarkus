package br.unitins.dto.questionario;

import java.util.Date;
import java.util.List;

import br.unitins.dto.topico.TopicoDTO;

import lombok.Getter;

@Getter
public class QuestionarioDTO {

    private String titulo;
    private String descricao;
    private List<TopicoDTO> topicos;
    private Boolean status;
    private Date dataCriacao;
    private Long idSubcategoria;

}
