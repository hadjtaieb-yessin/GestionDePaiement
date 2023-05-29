package com.bezkoder.spring.security.postgresql.Services;


import com.bezkoder.spring.security.postgresql.models.Employe;
import com.bezkoder.spring.security.postgresql.models.PaiementPersonnel;
import com.bezkoder.spring.security.postgresql.repository.EmployeRepository;
import com.bezkoder.spring.security.postgresql.repository.PaiementPersonnelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaiementPersonnelService {

    @Autowired
    PaiementPersonnelRepository paiementPersonnelRepository;

    public PaiementPersonnelService (PaiementPersonnelRepository paiementPersonnelRepository) {
        this.paiementPersonnelRepository= paiementPersonnelRepository;
    }

    public List<PaiementPersonnel> getAllPaiementPersonnels() {
        return paiementPersonnelRepository.findAll();
    }

    public Optional<PaiementPersonnel> isDataExist(PaiementPersonnel reqData){
        return paiementPersonnelRepository.findById(reqData.getReference());
    }

    public Object createPaiementPersonnel(PaiementPersonnel reqData){
        return paiementPersonnelRepository.save(reqData);
    }

    public void saveOrUpdate (PaiementPersonnel paiementPersonnel){
        paiementPersonnelRepository.save(paiementPersonnel);
    }

    public void deletePaiementPersonnel(Long reference) {
        paiementPersonnelRepository.deleteById(reference);
    }

    public void updatePaiementPersonnel(PaiementPersonnel paiementPersonnel , Long reference ) {
        paiementPersonnelRepository.save(paiementPersonnel);
    }



}
