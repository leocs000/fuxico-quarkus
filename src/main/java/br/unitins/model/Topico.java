package br.unitins.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Topico extends DefaultEntity{
    
    @Column
    private String nome;

    @Column
    private String descricao;

    @Column 
    private Double estrelas;

    @ManyToOne
    @JoinColumn(name = "id_questionario")
    private Questionario questionario;
}
