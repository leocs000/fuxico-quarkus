package br.unitins.repository;

import br.unitins.model.Categoria;
import br.unitins.model.Topico;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TopicoRepository implements PanacheRepository<Topico>{
    public PanacheQuery<Topico> findByNome(String nome){
        return find("UPPER(nome) LIKE UPPER(?1)", "%" + nome + "%");
    }


}
