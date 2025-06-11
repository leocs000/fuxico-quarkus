package br.unitins.service.avaliador;

import java.util.List;

import org.eclipse.microprofile.jwt.JsonWebToken;

import br.unitins.dto.avaliador.AvaliadorDTO;
import br.unitins.dto.avaliador.AvaliadorResponseDTO;
import br.unitins.model.Avaliador;
import br.unitins.model.Perfil;
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
        validarEmailAvaliador(dto.getEmail());
        validarCpfAvaliador(dto.getCpf());
        validarLoginAvaliador(dto.getLogin());

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

    @Transactional
    public AvaliadorResponseDTO insert(@Valid AvaliadorDTO dto, Integer perfil) {
        validarEmailAvaliador(dto.getEmail());
        validarCpfAvaliador(dto.getCpf());
        validarLoginAvaliador(dto.getLogin());

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
        usuario.setPerfil(Perfil.valueOf(perfil));
        
        usuarioRepository.persist(usuario);
        novoAvaliador.setUsuario(usuario);

        avaliadorRepository.persist(novoAvaliador);

        return AvaliadorResponseDTO.valueOf(novoAvaliador);
    }

    public void validarEmailAvaliador(String email) {
        Avaliador avaliador = avaliadorRepository.findByEmail(email);
        if (avaliador != null) {
            throw new ValidationException("email", "O email " + email + " já foi cadastrado");
        }
    }

    public void validarCpfAvaliador(String cpf) {
        Avaliador avaliador = avaliadorRepository.findByCpf(cpf);
        if (avaliador != null) {
            throw new ValidationException("cpf", "O cpf " + cpf + " ja foi cadastrado");
        }
    }

    public void validarLoginAvaliador(String login) {
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
            avaliador.setDataNacimento(dto.getDataNacimento());
            avaliador.setSerieAtual(dto.getSerieAtual());
            avaliador.setTipoAvaliador(tipoAvaliadorService.findById(dto.getIdTipoAvaliador()));

            avaliadorRepository.persist(avaliador);
        }

        return AvaliadorResponseDTO.valueOf(avaliador);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!avaliadorRepository.deleteById(id)) 
        throw new NotFoundException("Avaliador não encontrada com o ID: " + id);
    }

    @Override
    public AvaliadorResponseDTO findById(Long id) {
        return AvaliadorResponseDTO.valueOf(avaliadorRepository.findById(id));
    }

    @Override
    public List<AvaliadorResponseDTO> findByNome(String nome, int page, int pageSize) {
        List<Avaliador> list = avaliadorRepository.findByNome(nome).page(page, pageSize).list();
        return list.stream()
                .map(e -> AvaliadorResponseDTO.valueOf(e)).toList();
    }

    @Override
    public List<AvaliadorResponseDTO> findByAll(int page, int pageSize) {
        List<Avaliador> list = avaliadorRepository.findAll().page(page, pageSize).list();
        return list.stream().map(e -> AvaliadorResponseDTO.valueOf(e)).toList();
    }

    @Override
    public long count() {
        return avaliadorRepository.count();
    }

    @Override
    public long countByNome(String nome) {
        return avaliadorRepository.findByNome(nome).count();
    }

    @Override
    public Avaliador findByUsuario(String login) {
        Avaliador avaliador = avaliadorRepository.findByLogin(login);
        return avaliador;
    }

}
