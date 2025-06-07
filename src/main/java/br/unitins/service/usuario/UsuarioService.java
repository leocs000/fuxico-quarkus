package br.unitins.service.usuario;

import java.util.List;

import br.unitins.dto.usuario.AlterarLoginUsuarioDTO;
import br.unitins.dto.usuario.AlterarSenhaUsuarioDTO;
import br.unitins.dto.usuario.UsuarioDTO;
import br.unitins.dto.usuario.UsuarioResponseDTO;
import jakarta.validation.Valid;


public interface UsuarioService {

    public UsuarioResponseDTO insert(@Valid UsuarioDTO dto);

    public UsuarioResponseDTO update(UsuarioDTO dto, Long id);

    public UsuarioResponseDTO alterarSenha(AlterarSenhaUsuarioDTO alterarSenhaUsuarioDTO, String senha);
    
    public UsuarioResponseDTO alterarLogin(AlterarLoginUsuarioDTO alterarLoginUsuarioDTO, String login);

    public void delete(Long id);

    public UsuarioResponseDTO findById(Long id);

    public List<UsuarioResponseDTO> findByNome(String nome, int page, int pageSize);

    public UsuarioResponseDTO findByLoginAndSenha(String login, String senha);

    public UsuarioResponseDTO findByLogin(String login);

    public List<UsuarioResponseDTO> findByAll(int page, int pageSize);
    
    public UsuarioResponseDTO findMyUser();

    public long count();

    public long countByNome(String nome);

}
