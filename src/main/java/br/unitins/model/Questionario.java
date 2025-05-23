package br.unitins.model;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
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

    
    @OneToMany(mappedBy = "questionario")
    private List<Topico> topicos;

    @Column
    private Boolean status;

    @Column 
    private Date dataCriacao;
}
