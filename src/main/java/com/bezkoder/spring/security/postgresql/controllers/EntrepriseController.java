package com.bezkoder.spring.security.postgresql.controllers;

import com.bezkoder.spring.security.postgresql.Services.EntrepriseService;
import com.bezkoder.spring.security.postgresql.bean.ResultDTO;
import com.bezkoder.spring.security.postgresql.models.Entreprise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin (origins = "http://localhost:8080")
@RestController
@RequestMapping ("/api/entreprise")
public class EntrepriseController {

    @Autowired
    EntrepriseService entrepriseService;

//    @GetMapping ("/entreprise")
//    public List<Entreprise> getAllEntreprises(){
//        return EntrepriseService.getAllEse();
//    }

    @GetMapping("/AllEntreprises")
    public ResponseEntity<?> allEntreprise() {
        System.err.println(":::  EseController.entreprise :::");
        ResultDTO<?> responsePacket = null;
        try {
            responsePacket = new ResultDTO<>(entrepriseService.getAllEse(), "entreprise fetched successfully !!", true);
            return new ResponseEntity<>(responsePacket, HttpStatus.OK);
        } catch (Exception e) {
            responsePacket = new ResultDTO<>(e.getMessage(), false);
            return new ResponseEntity<>(responsePacket, HttpStatus.BAD_REQUEST);
        }
    }


}
