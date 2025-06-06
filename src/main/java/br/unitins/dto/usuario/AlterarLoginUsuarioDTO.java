package br.unitins.dto.usuario;

import jakarta.validation.constraints.NotBlank;

public record AlterarLoginUsuarioDTO(
    @NotBlank(message = "O campo nome não pode ser nulo.")
    String login
) {
} 
