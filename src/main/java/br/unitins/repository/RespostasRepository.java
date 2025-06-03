package br.unitins.repository;

import br.unitins.model.Respostas;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RespostasRepository implements PanacheRepository<Respostas>{
    public PanacheQuery<Respostas> findByNome(String nome){
        return find("UPPER(nome) LIKE UPPER(?1)", "%" + nome + "%");
    }
}
