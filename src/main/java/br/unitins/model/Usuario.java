package br.unitins.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Usuario extends DefaultEntity{
    private String login;
    private String senha;

    //@Enumerated(EnumType.ORDINAL)
    private Perfil perfil;
}
