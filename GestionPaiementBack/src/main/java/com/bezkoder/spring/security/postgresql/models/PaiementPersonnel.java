package com.bezkoder.spring.security.postgresql.models;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "paiementPersonnel")
public class PaiementPersonnel implements Serializable {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "reference")
    private Long reference;

    @Column(name = "salaireBrut")
    private Float salaireBrut;

    @Column(name = "salaireNet")
    private Float salaireNet;

    @Column(name = "prime")
    private Float prime;

    @Column(name = "avance")
    private Float avance;




}
