package com.bezkoder.spring.security.postgresql.controllers;


import com.bezkoder.spring.security.postgresql.Services.PaiementPersonnelService;
import com.bezkoder.spring.security.postgresql.bean.BeanValidator;
import com.bezkoder.spring.security.postgresql.bean.ResultDTO;
import com.bezkoder.spring.security.postgresql.models.PaiementPersonnel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/paiementPersonnel")
public class PaiementPersonnelController {

    @Autowired
    PaiementPersonnelService paiementPersonnelService;

    @Autowired
    private BeanValidator beanValidator;

    @GetMapping("/AllPaiementPersonnels")
    public ResponseEntity<?> allPaiementPersonnels() {
        System.err.println(":::  PaiementPersonnelController.getpaiementPersonnel :::");
        ResultDTO<?> responsePacket = null;
        try {
            responsePacket = new ResultDTO<>(paiementPersonnelService.getAllPaiementPersonnels(), "paiementPersonnel fetched successfully !!", true);
            return new ResponseEntity<>(responsePacket, HttpStatus.OK);
        } catch (Exception e) {
            responsePacket = new ResultDTO<>(e.getMessage(), false);
            return new ResponseEntity<>(responsePacket, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/createPaiementPersonnel")
    public ResponseEntity<?> createPaiementPersonnel(@RequestBody PaiementPersonnel reqData) {
        System.err.println(":::  PaiementPersonnelController.create PaiementPersonnel :::");
        ResultDTO<?> responsePacket = null;
        try {
            ArrayList<String> errorList = beanValidator.paimentPersonnelValidate(reqData);
            if (errorList.size() != 0) {
                ResultDTO<ArrayList<String>> errorPacket = new ResultDTO<>(errorList,
                        "Above fields values must not be empty", false);
                return new ResponseEntity<>(errorPacket, HttpStatus.BAD_REQUEST);
            }
            Optional<PaiementPersonnel> isData = paiementPersonnelService.isDataExist(reqData);
            if (isData == null) {
                responsePacket = new ResultDTO<>(paiementPersonnelService.createPaiementPersonnel(reqData), "PaiementPersonnel Created Successfully", true);
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

    @DeleteMapping ("deletePaiementPersonnel/{reference}")
    private void deletePaiementPersonnel (@PathVariable ("reference") String reference) {
        paiementPersonnelService.deletePaiementPersonnel(reference);
    }

    @PutMapping ("updatePaiementPersonnel")
    private PaiementPersonnel updatePaiementPersonnel (@RequestBody PaiementPersonnel paiementPersonnel  ) {
        paiementPersonnelService.saveOrUpdate(paiementPersonnel);
        return paiementPersonnel;
    }


}
