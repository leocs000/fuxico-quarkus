package br.unitins.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Categoria extends DefaultEntity{
    
    @Column
    private String nome;

}
