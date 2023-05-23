package com.bezkoder.spring.security.postgresql.controllers;


import com.bezkoder.spring.security.postgresql.Services.DepartementService;
import com.bezkoder.spring.security.postgresql.bean.BeanValidator;
import com.bezkoder.spring.security.postgresql.bean.ResultDTO;
import com.bezkoder.spring.security.postgresql.models.Departement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@CrossOrigin (origins = "http://localhost:8080")
@RestController
@RequestMapping ("/api/departement")
public class DepartementController {

    @Autowired
    DepartementService departementService;

    @Autowired
    private BeanValidator beanValidator;

    @GetMapping("/AllDepartements")
    public ResponseEntity<?> allDepartements() {
        System.err.println(":::  DepartementController.getdepartement :::");
        ResultDTO<?> responsePacket = null;
        try {
            responsePacket = new ResultDTO<>(departementService.getAllDepartements(), "departement fetched successfully !!", true);
            return new ResponseEntity<>(responsePacket, HttpStatus.OK);
        } catch (Exception e) {
            responsePacket = new ResultDTO<>(e.getMessage(), false);
            return new ResponseEntity<>(responsePacket, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/createDepartement")
    public ResponseEntity<?> createDepartement(@RequestBody Departement reqData) {
        System.err.println(":::  DepartementController.create Departement :::");
        ResultDTO<?> responsePacket = null;
        try {
            ArrayList<String> errorList = beanValidator.departementValidate(reqData);
            if (errorList.size() != 0) {
                ResultDTO<ArrayList<String>> errorPacket = new ResultDTO<>(errorList,
                        "Above fields values must not be empty", false);
                return new ResponseEntity<>(errorPacket, HttpStatus.BAD_REQUEST);
            }
            Departement isData = departementService.isDataExist(reqData);
            if (isData == null) {
                responsePacket = new ResultDTO<>(departementService.createDepartement(reqData), "Departement Created Successfully", true);
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

    @DeleteMapping ("deleteDepartement/{id}")
    private void deleteDepartement (@PathVariable ("id") Long id) {
        departementService.deleteDepartement(id);
    }

    @PutMapping ("updateDepartement")
    private Departement updateDepartement (@RequestBody Departement departement) {
        departementService.saveOrUpdate(departement);
        return departement;
    }



}
