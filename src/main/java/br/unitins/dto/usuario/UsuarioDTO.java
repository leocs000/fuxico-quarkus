package br.unitins.dto.usuario;

import jakarta.validation.constraints.NotBlank;

public record UsuarioDTO(
    @NotBlank(message = "O campo login não pode estar em branco")
    String login,
    @NotBlank(message = "O campo senha não pode estar em branco")
    String senha,
    Integer idPerfil
) {
} 
