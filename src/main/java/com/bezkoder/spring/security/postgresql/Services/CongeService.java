package com.bezkoder.spring.security.postgresql.Services;


import com.bezkoder.spring.security.postgresql.models.Conge;
import com.bezkoder.spring.security.postgresql.repository.CongeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CongeService {

    @Autowired
    CongeRepository congeRepository;

    public CongeService (CongeRepository congeRepository) {
        this.congeRepository= congeRepository;
    }

    public List<Conge> getAllConges() {
        return congeRepository.findAll();
    }

    public Optional<Conge> isDataExist(Conge reqData){
        return congeRepository.findById(reqData.getId());
    }

    public Object createConge(Conge reqData){
        return congeRepository.save(reqData);
    }

    public void saveOrUpdate (Conge conge){
        congeRepository.save(conge);
    }

    public void deleteConge(Long id){
        congeRepository.deleteById(id);
    }

    public void updateConge(Conge conge , Long id) {
        congeRepository.save(conge);
    }



}
