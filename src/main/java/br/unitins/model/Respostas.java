package br.unitins.model;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class Respostas extends DefaultEntity{

    @ManyToOne
    @JoinColumn(name = "id_avaliacao")
    private Avaliacao avaliacao;

    @ManyToOne
    @JoinColumn(name = "id_topico")
    private Topico topico;

    @Column
    private Double estrela;
}
