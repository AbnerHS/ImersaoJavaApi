package br.com.abner.apilanguages.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.abner.apilanguages.Model.Language;
import br.com.abner.apilanguages.Repository.LinguagemRepository;

@RestController
public class LanguageController {

    @Autowired
    private LinguagemRepository repository;

    @GetMapping("/languages")
    public List<Language> getLanguages(){
        return repository.findByOrderByRanking();        
    }

    @GetMapping("/language/{id}")
    public Language getLanguageById(@PathVariable String id){
        return repository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/language")
    public Language getLanguageByTitle(@RequestParam String title){
        return repository.findByTitle(title);
    }

    @PostMapping("/language")
    public ResponseEntity<Language> addLanguage(@RequestBody Language language){
        return new ResponseEntity<>(repository.insert(language), HttpStatus.CREATED);
    }

    @PutMapping("/language/{id}")
    public Language updateLanguage(@PathVariable String id, @RequestBody Language language){
        if(!repository.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        language.setId(id);
        return repository.save(language);
    }

    @DeleteMapping("/language/{id}")
    public ResponseEntity<HttpStatusCode> removeLanguage(@PathVariable String id){
        if(!repository.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        repository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
