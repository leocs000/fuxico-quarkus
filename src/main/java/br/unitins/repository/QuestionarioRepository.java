package br.unitins.repository;

import br.unitins.model.Questionario;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class QuestionarioRepository implements PanacheRepository<Questionario>{
    public PanacheQuery<Questionario> findByNome(String nome){
        return find("UPPER(nome) LIKE UPPER(?1)", "%" + nome + "%");
    }

}
