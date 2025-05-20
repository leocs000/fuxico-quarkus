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
public class SubCategoria extends DefaultEntity{
    
    @Column
    private String nome;

    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;
}
