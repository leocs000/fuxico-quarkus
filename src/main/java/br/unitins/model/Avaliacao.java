package br.unitins.model;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
    
    @Column
    private Double toxicidade;

    @Column
    private boolean visibiliadade;
    
    @ManyToOne
    @JoinColumn(name = "id_questionario")
    private Questionario questionario;

    @OneToMany(mappedBy = "avaliacao")
    private List<Respostas> respostas;

    @ManyToOne
    @JoinColumn(name = "id_avaliador")
    private Avaliador avaliador;
}
