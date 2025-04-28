package br.unitins.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Topico extends DefaultEntity{

    @Column
    private String nome;

    @Column
    private Double estrela;

    @Column
    private Questionario questionario;
}
