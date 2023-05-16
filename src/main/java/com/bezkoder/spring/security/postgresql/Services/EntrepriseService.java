package com.bezkoder.spring.security.postgresql.Services;

import com.bezkoder.spring.security.postgresql.models.Entreprise;
import com.bezkoder.spring.security.postgresql.repository.EntrepriseRepository;
import com.bezkoder.spring.security.postgresql.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntrepriseService {


    @Autowired
    private EntrepriseRepository entrepriseRepository;

    public EntrepriseService(EntrepriseRepository entrepriseRepository) {
        this.entrepriseRepository=entrepriseRepository;
    }
    public List<Entreprise> getAllEse() {
        return entrepriseRepository.findAll();
    }

    public Entreprise isDataExist(Entreprise reqData) {
        return entrepriseRepository.findByEmail(reqData.getEmail());
    }

    public Object createEntreprise(Entreprise reqData) {
        return entrepriseRepository.save(reqData);
    }


}
