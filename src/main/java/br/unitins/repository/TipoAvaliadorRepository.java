package br.unitins.repository;

import br.unitins.model.TipoAvaliador;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TipoAvaliadorRepository implements PanacheRepository<TipoAvaliador>{
    public PanacheQuery<TipoAvaliador> findByNome(String nome){
        return find("UPPER(nome) LIKE UPPER(?1)", "%" + nome + "%");
    }
}
