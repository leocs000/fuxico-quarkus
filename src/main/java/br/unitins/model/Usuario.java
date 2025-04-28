package br.unitins.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Usuario extends DefaultEntity{

    @Column
    private String nome;

    @Column
    private String email;

    @Column
    private String senha;

    @Column
    private String cpf;
}
