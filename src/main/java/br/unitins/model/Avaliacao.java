package br.unitins.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Avaliacao extends DefaultEntity{
    @Column
    private Date dataAvaliacao;
    @Column
    private String comentario;
    @ManyToOne
    @JoinColumn(name = "id_questionario")
    private Questionario questionario;

//    @ManyToOne
//    @JoinColumn(name = "id_avaliador")
//    private Usuario usuario;
}
