package br.unitins.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Questionario extends DefaultEntity{

    @Column
    private String titulo;

    @Column
    private String descricao;

    @Column
    private Topico topico;

    @Column
    private Boolean status;

    @Column 
    private Date dataCriacao;

}