package br.unitins.dto.avaliador;

import java.util.Date;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class AvaliadorDTO {
    
    String nome;
    String cpf;
    String email;
    Date dataNacimento;
    String serieAtual;
    Long idTipoAvaliador;
    @NotNull(message = "O campo login não pode ficar em branco")
    String login;
    
    @NotNull(message = "O campo senha não pode ficar em branco")
    String senha;
}
