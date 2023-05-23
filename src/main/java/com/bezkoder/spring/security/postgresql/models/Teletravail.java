package com.bezkoder.spring.security.postgresql.models;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table (name = "teletravail")
public class Teletravail implements Serializable {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Long id;

    @Column (name = "dateDebutTeletravail")
    private Date dateDebutTeletravail;

    @Column (name = "dateFinTeletravail")
    private Date dateFinTeletravail;


}
