package com.bezkoder.spring.security.postgresql.controllers;

import com.bezkoder.spring.security.postgresql.Services.TeletravailService;
import com.bezkoder.spring.security.postgresql.bean.BeanValidator;
import com.bezkoder.spring.security.postgresql.bean.ResultDTO;
import com.bezkoder.spring.security.postgresql.models.Teletravail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;


@CrossOrigin (origins = "http://localhost:8080")
@RestController
@RequestMapping ("/api/teletravail")
public class TeletravailController {

    @Autowired
    TeletravailService teletravailService;

    @Autowired
    private BeanValidator beanValidator;

    @GetMapping("/AllTeletravails")
    public ResponseEntity<?> allTeletravails() {
        System.err.println(":::  TeletravailController.getteletravail :::");
        ResultDTO<?> responsePacket = null;
        try {
            responsePacket = new ResultDTO<>(teletravailService.getAllTeletravails(), "teletravail fetched successfully !!", true);
            return new ResponseEntity<>(responsePacket, HttpStatus.OK);
        } catch (Exception e) {
            responsePacket = new ResultDTO<>(e.getMessage(), false);
            return new ResponseEntity<>(responsePacket, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/createTeletravail")
    public ResponseEntity<?> createTeletravail(@RequestBody Teletravail reqData) {
        System.err.println(":::  TeletravailController.create Teletravail :::");
        ResultDTO<?> responsePacket = null;
        try {
            ArrayList<String> errorList = beanValidator.teletravailValidate(reqData);
            if (errorList.size() != 0) {
                ResultDTO<ArrayList<String>> errorPacket = new ResultDTO<>(errorList,
                        "Above fields values must not be empty", false);
                return new ResponseEntity<>(errorPacket, HttpStatus.BAD_REQUEST);
            }
            Optional<Teletravail> isData = teletravailService.isDataExist(reqData);
            if (isData == null) {
                responsePacket = new ResultDTO<>(teletravailService.createTeletravail(reqData), "Teletravail Created Successfully", true);
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

    @DeleteMapping("deleteTeletravail/{id}")
    private void deleteTeletravail (@PathVariable("id") Long id) {
        teletravailService.deleteTeletravail(id);
    }

    @PutMapping ("updateTeletravail")
    private Teletravail updateTeletravail (@RequestBody Teletravail teletravail ) {
        teletravailService.saveOrUpdate(teletravail);
        return teletravail;
    }
}
