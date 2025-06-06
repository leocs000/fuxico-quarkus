package br.unitins.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Avaliador extends DefaultEntity{
    @Column(length = 60)
    private String nome;

    @Column(length = 20)
    private String cpf;

    @Column(length = 100)
    private String email;

    @Column
    private Date dataNacimento;

    @Column
    private String serieAtual;

    @ManyToOne
    @JoinColumn(name = "id_tipo_avaliador")
    private TipoAvaliador tipoAvaliador;

    @OneToOne
    @JoinTable(name = "cliente_usuario", joinColumns = @JoinColumn(name = "id_usuario"), inverseJoinColumns = @JoinColumn(name = "id_cliente"))
    private Usuario usuario;
}
