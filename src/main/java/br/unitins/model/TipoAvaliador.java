package br.unitins.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class TipoAvaliador extends DefaultEntity{

    @Column
    private String descricao;
}
