package br.unitins.dto.usuario;

public record AuthUsuarioDTO(
    String login,
    String senha,
    int perfil
) {
} 