package br.unitins.repository;

import br.unitins.model.Categoria;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CategoriaRepository implements PanacheRepository<Categoria>{
    public PanacheQuery<Categoria> findByNome(String nome){
        return find("UPPER(nome) LIKE UPPER(?1)", "%" + nome + "%");
    }

}
