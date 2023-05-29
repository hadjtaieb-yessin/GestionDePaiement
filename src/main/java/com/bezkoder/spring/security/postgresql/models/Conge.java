package com.bezkoder.spring.security.postgresql.models;


import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table (name = "conge")
public class Conge implements Serializable {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Long id;

    @NotBlank
    @Size (max = 100)
    @Column (name = "descriptionConge")
    private String descriptionConge;

    @Column (name = "dateDebutConge")
    private Date dateDebutConge;

    @Column (name = "dateFinConge")
    private Date dateFinConge;

    @Enumerated (EnumType.STRING)
    @Column
    private EConge type;

    @ManyToOne
    @JoinTable(name = "employe_conges")
    private Employe employe;


}
