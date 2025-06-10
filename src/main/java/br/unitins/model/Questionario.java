package br.unitins.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

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
public class Questionario extends DefaultEntity{

    @Column
    private String titulo;

    @Column
    private String descricao;

    
    @OneToMany(mappedBy = "questionario")
    @JsonManagedReference
    private List<Topico> topicos;

    @Column
    private Boolean status;

    @Column 
    private Date dataCriacao;

    @ManyToOne
    @JoinColumn(name = "id_subcategoria")
    private SubCategoria subcategoria;
}
