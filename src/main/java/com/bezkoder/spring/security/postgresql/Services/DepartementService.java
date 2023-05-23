package com.bezkoder.spring.security.postgresql.Services;

import com.bezkoder.spring.security.postgresql.models.Departement;
import com.bezkoder.spring.security.postgresql.repository.DepartementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartementService {

    @Autowired
    DepartementRepository departementRepository;

    public DepartementService (DepartementRepository departementRepository) {
        this.departementRepository= departementRepository;
    }

    public List<Departement> getAllDepartements() {
        return departementRepository.findAll();
    }

    public Departement isDataExist(Departement reqData){
        return departementRepository.findByName(reqData.getName());
    }

    public Object createDepartement(Departement reqData){
        return departementRepository.save(reqData);
    }

    public void saveOrUpdate (Departement departement){
        departementRepository.save(departement);
    }

    public void deleteDepartement(Long id){
        departementRepository.deleteById(id);
    }

    public void updateDepartement(Departement departement, Long id) {
        departementRepository.save(departement);
    }




}
