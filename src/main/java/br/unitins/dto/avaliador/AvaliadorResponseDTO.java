package br.unitins.dto.avaliador;

import java.util.Date;

import br.unitins.dto.tipoAvaliador.TipoAvaliadorResponseDTO;
import br.unitins.dto.usuario.UsuarioResponseDTO;
import br.unitins.model.Avaliador;



public record AvaliadorResponseDTO(
    Long id,
    String nome,
    String cpf,
    String email,
    Date dataNacimento,
    String serieAtual,
    TipoAvaliadorResponseDTO tipoAvaliador,
    UsuarioResponseDTO usuario
) {
    public static AvaliadorResponseDTO valueOf(Avaliador avaliador){
        return new AvaliadorResponseDTO(
            avaliador.getId(), 
            avaliador.getNome(),
            avaliador.getCpf(),
            avaliador.getEmail(),
            avaliador.getDataNacimento(),
            avaliador.getSerieAtual(),
            TipoAvaliadorResponseDTO.valueOf(avaliador.getTipoAvaliador()),
            UsuarioResponseDTO.valueOf(avaliador.getUsuario()));
    }
}