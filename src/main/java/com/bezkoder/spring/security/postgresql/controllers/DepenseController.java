package com.bezkoder.spring.security.postgresql.controllers;


import com.bezkoder.spring.security.postgresql.Services.DepenseService;
import com.bezkoder.spring.security.postgresql.bean.BeanValidator;
import com.bezkoder.spring.security.postgresql.bean.ResultDTO;
import com.bezkoder.spring.security.postgresql.models.Depense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@CrossOrigin (origins = "http://localhost:8080")
@RestController
@RequestMapping ("/api/depense")
public class DepenseController {

    @Autowired
    DepenseService depenseService;

    @Autowired
    private BeanValidator beanValidator;

    @GetMapping("/AllDepense")
    public ResponseEntity<?> allDepenses() {
        System.err.println(":::  DepenseController.getdepense :::");
        ResultDTO<?> responsePacket = null;
        try {
            responsePacket = new ResultDTO<>(depenseService.getAllDepenses(), "depense fetched successfully !!", true);
            return new ResponseEntity<>(responsePacket, HttpStatus.OK);
        } catch (Exception e) {
            responsePacket = new ResultDTO<>(e.getMessage(), false);
            return new ResponseEntity<>(responsePacket, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/createDepense")
    public ResponseEntity<?> createDepense(@RequestBody Depense reqData) {
        System.err.println(":::  DepenseController.create Depense :::");
        ResultDTO<?> responsePacket = null;
        try {
            ArrayList<String> errorList = beanValidator.depenseValidate(reqData);
            if (errorList.size() != 0) {
                ResultDTO<ArrayList<String>> errorPacket = new ResultDTO<>(errorList,
                        "Above fields values must not be empty", false);
                return new ResponseEntity<>(errorPacket, HttpStatus.BAD_REQUEST);
            }
            Optional<Depense> isData = depenseService.isDataExist(reqData);
            if (isData == null) {
                responsePacket = new ResultDTO<>(depenseService.createDepense(reqData), "Depense Created Successfully", true);
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

    @DeleteMapping ("deleteDepense/{id}")
    private void deleteDepense (@PathVariable ("id") Long id) {
        depenseService.deleteDepense(id);
    }

    @PutMapping ("updateDepense")
    private Depense updateDepense (@RequestBody Depense depense) {
        depenseService.saveOrUpdate(depense);
        return depense;
    }



}
