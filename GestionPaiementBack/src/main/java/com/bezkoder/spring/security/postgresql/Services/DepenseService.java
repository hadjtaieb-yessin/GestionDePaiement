package com.bezkoder.spring.security.postgresql.Services;


import com.bezkoder.spring.security.postgresql.models.Depense;
import com.bezkoder.spring.security.postgresql.repository.DepenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepenseService {

    @Autowired
    DepenseRepository depenseRepository;

    public DepenseService (DepenseRepository depenseRepository) {
        this.depenseRepository= depenseRepository;
    }

    public List<Depense> getAllDepenses() {
        return depenseRepository.findAll();
    }

    public Optional<Depense> isDataExist(Depense reqData){
        return depenseRepository.findById(reqData.getId());
    }

    public Object createDepense(Depense reqData){
        return depenseRepository.save(reqData);
    }

    public void saveOrUpdate (Depense depense){
        depenseRepository.save(depense);
    }

    public void deleteDepense(Long id){
        depenseRepository.deleteById(id);
    }


}
