package com.bezkoder.spring.security.postgresql.controllers;

import com.bezkoder.spring.security.postgresql.Services.EntrepriseService;
import com.bezkoder.spring.security.postgresql.bean.BeanValidator;
import com.bezkoder.spring.security.postgresql.bean.ResultDTO;
import com.bezkoder.spring.security.postgresql.models.Entreprise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin (origins = "http://localhost:8080")
@RestController
@RequestMapping ("/api/entreprise")
public class EntrepriseController {

    @Autowired
    EntrepriseService entrepriseService;

    @Autowired
    private BeanValidator beanValidator;
//    @GetMapping ("/entreprise")
//    public List<Entreprise> getAllEntreprises(){
//        return EntrepriseService.getAllEse();
//    }

    @GetMapping("/AllEntreprises")
    public ResponseEntity<?> allEntreprise() {
        System.err.println(":::  EseController.getentreprise :::");
        ResultDTO<?> responsePacket = null;
        try {
            responsePacket = new ResultDTO<>(entrepriseService.getAllEse(), "entreprise fetched successfully !!", true);
            return new ResponseEntity<>(responsePacket, HttpStatus.OK);
        } catch (Exception e) {
            responsePacket = new ResultDTO<>(e.getMessage(), false);
            return new ResponseEntity<>(responsePacket, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/createEse")
    public ResponseEntity<?> createEntreprise(@RequestBody Entreprise reqData) {
        System.err.println(":::  EseController.create Ese :::");
        ResultDTO<?> responsePacket = null;
        try {
            ArrayList<String> errorList = beanValidator.entrepriseValidate(reqData);
            if (errorList.size() != 0) {
                ResultDTO<ArrayList<String>> errorPacket = new ResultDTO<>(errorList,
                        "Above fields values must not be empty", false);
                return new ResponseEntity<>(errorPacket, HttpStatus.BAD_REQUEST);
            }
            Entreprise isData = entrepriseService.isDataExist(reqData);
            if (isData == null) {
                responsePacket = new ResultDTO<>(entrepriseService.createEntreprise(reqData), "Entreprise Created Successfully", true);
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
}
