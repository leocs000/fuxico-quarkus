package br.unitins.repository;

import br.unitins.model.Avaliador;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AvaliadorRepository implements PanacheRepository<Avaliador>{
    public PanacheQuery<Avaliador> findByNome(String nome) {
        return find("UPPER(nome) LIKE UPPER(?1) ", "%" + nome + "%");
    }

    public Avaliador findByCpf(String cpf) {
        return find("UPPER(cpf) LIKE ?1", "%" + cpf.toUpperCase() + "%").firstResult();
    }

    public Avaliador findByEmail(String email){
        return find("UPPER(email) LIKE ?1", "%" + email.toUpperCase() + "%").firstResult();
    }

    public Avaliador findByLoginAndSenha(String login, String senha) {
        return find("usuario.login = ?1 AND usuario.senha = ?2", login, senha).firstResult();
    }

    public Avaliador findByLogin(String login) {
        return find("usuario.login = ?1", login).firstResult();
    }
}
