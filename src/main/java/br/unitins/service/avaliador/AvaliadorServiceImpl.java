package br.unitins.service.avaliador;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.microprofile.jwt.JsonWebToken;

import br.unitins.dto.avaliador.AvaliadorDTO;
import br.unitins.dto.avaliador.AvaliadorResponseDTO;
import br.unitins.model.Avaliador;
import br.unitins.model.Perfil;
import br.unitins.model.TipoAvaliador;
import br.unitins.model.Usuario;
import br.unitins.repository.AvaliadorRepository;
import br.unitins.repository.UsuarioRepository;
import br.unitins.service.hash.HashService;
import br.unitins.service.tipoAvaliador.TipoAvaliadorService;
import br.unitins.validation.ValidationException;
import io.quarkus.security.identity.SecurityIdentity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class AvaliadorServiceImpl implements AvaliadorService{

    @Inject
    AvaliadorRepository avaliadorRepository;

    @Inject
    UsuarioRepository usuarioRepository;

    @Inject
    public HashService hashService;

    @Inject
    TipoAvaliadorService tipoAvaliadorService;

    @Inject
    SecurityIdentity securityIdentity;

    @Inject
    JsonWebToken jwt;

    @Override
    @Transactional
    public AvaliadorResponseDTO insert(@Valid AvaliadorDTO dto) {
        validarEmailCliente(dto.getEmail());
        validarCpfCliente(dto.getCpf());
        validarLoginCliente(dto.getLogin());

        Avaliador novoAvaliador = new Avaliador();
        novoAvaliador.setNome(dto.getNome());
        novoAvaliador.setCpf(dto.getCpf());
        novoAvaliador.setEmail(dto.getEmail());
        novoAvaliador.setDataNacimento(dto.getDataNacimento());
        novoAvaliador.setSerieAtual(dto.getSerieAtual());
        novoAvaliador.setTipoAvaliador(tipoAvaliadorService.findById(dto.getIdTipoAvaliador()));

        Usuario usuario = new Usuario();
        usuario.setLogin(dto.getLogin());
        usuario.setSenha(hashService.getHashSenha(dto.getSenha()));
        usuario.setPerfil(Perfil.AVALIADOR);
        
        usuarioRepository.persist(usuario);
        novoAvaliador.setUsuario(usuario);

        avaliadorRepository.persist(novoAvaliador);

        return AvaliadorResponseDTO.valueOf(novoAvaliador);
    }

    public void validarEmailCliente(String email) {
        Avaliador avaliador = avaliadorRepository.findByEmail(email);
        if (avaliador != null) {
            throw new ValidationException("email", "O email " + email + " já foi cadastrado");
        }
    }

    public void validarCpfCliente(String cpf) {
        Avaliador avaliador = avaliadorRepository.findByCpf(cpf);
        if (avaliador != null) {
            throw new ValidationException("cpf", "O cpf " + cpf + " ja foi cadastrado");
        }
    }

    public void validarLoginCliente(String login) {
        Avaliador avaliador = avaliadorRepository.findByLogin(login);
        if (avaliador != null) {
            throw new ValidationException("Login", "O Login " + login + " já foi cadastrado");
        }
    }

    @Override
    @Transactional
    public AvaliadorResponseDTO update(AvaliadorDTO dto, Long id) {
        Avaliador avaliador = avaliadorRepository.findById(id);
        if (avaliador == null) {
            throw new NotFoundException("Avaliador não encontrado");
        }

        Usuario usuario = avaliador.getUsuario();
        avaliador.setUsuario(usuario);
        
        if (avaliador != null) {
            avaliador.setNome(dto.getNome());
            avaliador.setCpf(dto.getCpf());
            avaliador.setEmail(dto.getEmail());

            avaliadorRepository.persist(avaliador);
        }

        return AvaliadorResponseDTO.valueOf(avaliador);
    }

    @Override
    public void delete(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public AvaliadorResponseDTO findById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public AvaliadorResponseDTO findByUsuario(String login) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByUsuario'");
    }

    @Override
    public List<AvaliadorResponseDTO> findByNome(String nome, int page, int pageSize) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByNome'");
    }

    @Override
    public List<AvaliadorResponseDTO> findByAll(int page, int pageSize) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByAll'");
    }

    @Override
    public long count() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'count'");
    }

    @Override
    public long countByNome(String nome) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'countByNome'");
    }

}
