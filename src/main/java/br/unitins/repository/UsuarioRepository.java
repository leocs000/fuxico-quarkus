package br.unitins.repository;

import br.unitins.model.Usuario;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.NoResultException;
import jakarta.validation.ValidationException;

@ApplicationScoped
public class UsuarioRepository implements PanacheRepository<Usuario>{
    public PanacheQuery<Usuario> findByNome(String nome) {
        return find("UPPER(nome) LIKE UPPER(?1) ", "%"+nome+"%");
    }

    public Usuario findByLogin(String login) {
        try {
            return find("login = ?1 ", login ).singleResult();
        } catch (NoResultException e) {
            e.printStackTrace();
            return null;
        }
        
    }

    public Usuario findByLoginAndSenha(String login, String senha) {
        try {
            return find("login = ?1 and senha = ?2", login, senha).singleResult();
        } catch (NoResultException e) {
            e.printStackTrace();
            throw new ValidationException("Login ou senha inválido");
        }
    }
}