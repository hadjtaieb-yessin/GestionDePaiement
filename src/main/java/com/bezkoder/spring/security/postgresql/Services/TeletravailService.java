package com.bezkoder.spring.security.postgresql.Services;


import com.bezkoder.spring.security.postgresql.models.Conge;
import com.bezkoder.spring.security.postgresql.models.Teletravail;
import com.bezkoder.spring.security.postgresql.repository.CongeRepository;
import com.bezkoder.spring.security.postgresql.repository.TeletravailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeletravailService {

    @Autowired
    TeletravailRepository teletravailRepository;

    public TeletravailService (TeletravailRepository teletravailRepository) {
        this.teletravailRepository= teletravailRepository;
    }

    public List<Teletravail> getAllTeletravails() {
        return teletravailRepository.findAll();
    }

    public Optional<Teletravail> isDataExist(Teletravail reqData){
        return teletravailRepository.findById(reqData.getId());
    }

    public Object createTeletravail(Teletravail reqData){
        return teletravailRepository.save(reqData);
    }

    public void saveOrUpdate (Teletravail teletravail ){
        teletravailRepository.save(teletravail);
    }

    public void deleteTeletravail(Long id){
        teletravailRepository.deleteById(id);
    }

    public void updateTeletravail( Teletravail teletravail , Long id) {
        teletravailRepository.save(teletravail);
    }




}
