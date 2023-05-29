package com.bezkoder.spring.security.postgresql.Services;


import com.bezkoder.spring.security.postgresql.models.Employe;
import com.bezkoder.spring.security.postgresql.repository.EmployeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeService {

    @Autowired
    EmployeRepository employeRepository;

    public EmployeService (EmployeRepository employeRepository) {
        this.employeRepository= employeRepository;
    }

    public List<Employe> getAllEmployes() {
        return employeRepository.findAll();
    }

    public Optional<Employe> isDataExist(Employe reqData){
        return employeRepository.findById(reqData.getCIN());
    }

    public Object createEmploye(Employe reqData){
        return employeRepository.save(reqData);
    }

    public void saveOrUpdate (Employe employe){
        employeRepository.save(employe);
    }

    public void deleteEmploye(String CIN) {
        employeRepository.deleteById(CIN);
    }

    public void updateEmploye(Employe employe , String CIN) {
        employeRepository.save(employe);
    }



}
