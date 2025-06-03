package br.unitins.repository;

import br.unitins.model.Avaliacao;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AvaliacaoRepository implements PanacheRepository<Avaliacao>{
    public PanacheQuery<Avaliacao> findByNome(String nome){
        return find("UPPER(nome) LIKE UPPER(?1)", "%" + nome + "%");
    }

    public PanacheQuery<Avaliacao> listarAvaliacoesPendentes(){
        return find("toxicidade >= 0.0 AND aprovado = false");
    }
}