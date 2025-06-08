package br.unitins.service.jwt;

import br.unitins.dto.usuario.UsuarioResponseDTO;

public interface JwtService {
    
    public String generateJwt(UsuarioResponseDTO dto);
    
} 
