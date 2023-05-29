package com.bezkoder.spring.security.postgresql.controllers;


import com.bezkoder.spring.security.postgresql.Services.EmployeService;
import com.bezkoder.spring.security.postgresql.bean.BeanValidator;
import com.bezkoder.spring.security.postgresql.bean.ResultDTO;
import com.bezkoder.spring.security.postgresql.models.Employe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/employe")
public class EmployeController {

    @Autowired
    EmployeService employeService;

    @Autowired
    private BeanValidator beanValidator;

    @GetMapping("/AllEmployes")
    public ResponseEntity<?> allEmployes() {
        System.err.println(":::  EmployeController.getemploye :::");
        ResultDTO<?> responsePacket = null;
        try {
            responsePacket = new ResultDTO<>(employeService.getAllEmployes(), "employe fetched successfully !!", true);
            return new ResponseEntity<>(responsePacket, HttpStatus.OK);
        } catch (Exception e) {
            responsePacket = new ResultDTO<>(e.getMessage(), false);
            return new ResponseEntity<>(responsePacket, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/createEmploye")
    public ResponseEntity<?> createEmploye(@RequestBody Employe reqData) {
        System.err.println(":::  EmployeController.create Employe :::");
        ResultDTO<?> responsePacket = null;
        try {
            ArrayList<String> errorList = beanValidator.employeValidate(reqData);
            if (errorList.size() != 0) {
                ResultDTO<ArrayList<String>> errorPacket = new ResultDTO<>(errorList,
                        "Above fields values must not be empty", false);
                return new ResponseEntity<>(errorPacket, HttpStatus.BAD_REQUEST);
            }
            Optional<Employe> isData = employeService.isDataExist(reqData);
            if (isData == null) {
                responsePacket = new ResultDTO<>(employeService.createEmploye(reqData), "Employe Created Successfully", true);
                return new ResponseEntity<>(responsePacket, HttpStatus.OK);
            } else {
                responsePacket = new ResultDTO<>("Record already exist", false);
                return new ResponseEntity<>(responsePacket, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            responsePacket = new ResultDTO<>(e.getMessage(), false);
            return new ResponseEntity<>(responsePacket, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping ("deleteEmploye/{CIN}")
    private void deleteEmploye (@PathVariable ("CIN") String CIN) {
        employeService.deleteEmploye(CIN);
    }

    @PutMapping ("updateEmploye")
    private Employe updateEmploye (@RequestBody Employe employe ) {
        employeService.saveOrUpdate(employe);
        return employe;
    }


}
