package br.com.abner.apilanguages.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.abner.apilanguages.Model.Language;

public interface LinguagemRepository extends MongoRepository<Language, String> {
    
    public List<Language> findByOrderByRanking();

    public List<Language> findByOrderByTitle();

    public Language findByTitle(String title);
}
