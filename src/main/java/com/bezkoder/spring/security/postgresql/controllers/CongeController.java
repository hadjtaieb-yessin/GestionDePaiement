package com.bezkoder.spring.security.postgresql.controllers;


import com.bezkoder.spring.security.postgresql.Services.CongeService;
import com.bezkoder.spring.security.postgresql.bean.BeanValidator;
import com.bezkoder.spring.security.postgresql.bean.ResultDTO;
import com.bezkoder.spring.security.postgresql.models.Conge;
import com.bezkoder.spring.security.postgresql.models.Departement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@CrossOrigin (origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/conge")
public class CongeController {

    @Autowired
    CongeService congeService;

    @Autowired
    private BeanValidator beanValidator;

    @GetMapping("/AllConges")
    public ResponseEntity<?> allConges() {
        System.err.println(":::  CongeController.getconge :::");
        ResultDTO<?> responsePacket = null;
        try {
            responsePacket = new ResultDTO<>(congeService.getAllConges(), "conge fetched successfully !!", true);
            return new ResponseEntity<>(responsePacket, HttpStatus.OK);
        } catch (Exception e) {
            responsePacket = new ResultDTO<>(e.getMessage(), false);
            return new ResponseEntity<>(responsePacket, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/createConge")
    public ResponseEntity<?> createConge(@RequestBody Conge reqData) {
        System.err.println(":::  CongeController.create Conge :::");
        ResultDTO<?> responsePacket = null;
        try {
            ArrayList<String> errorList = beanValidator.congeValidate(reqData);
            if (errorList.size() != 0) {
                ResultDTO<ArrayList<String>> errorPacket = new ResultDTO<>(errorList,
                        "Above fields values must not be empty", false);
                return new ResponseEntity<>(errorPacket, HttpStatus.BAD_REQUEST);
            }
            Optional<Conge> isData = congeService.isDataExist(reqData);
            if (isData == null) {
                responsePacket = new ResultDTO<>(congeService.createConge(reqData), "Conge Created Successfully", true);
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

    @DeleteMapping ("deleteConge/{id}")
    private void deleteConge (@PathVariable ("id") Long id) {
        congeService.deleteConge(id);
    }

    @PutMapping ("updateConge")
    private Conge updateConge (@RequestBody Conge conge) {
        congeService.saveOrUpdate(conge);
        return conge;
    }



}
