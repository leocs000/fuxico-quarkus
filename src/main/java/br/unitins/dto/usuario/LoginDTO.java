package br.unitins.dto.usuario;

import jakarta.validation.constraints.NotEmpty;

public record LoginDTO(
    @NotEmpty(message = "O campo login não pode ser nulo.")
    String login,
    @NotEmpty(message = "O campo senha não pode ser nulo.")
    String senha
) {
    
}
