package br.unitins.repository;

import br.unitins.model.SubCategoria;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SubCategoriaRepository implements PanacheRepository<SubCategoria>{
    public PanacheQuery<SubCategoria> findByNome(String nome){
        return find("UPPER(material) LIKE UPPER(?1)", "%" + nome + "%");
    }

}
