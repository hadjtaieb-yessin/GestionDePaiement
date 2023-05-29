package com.bezkoder.spring.security.postgresql.models;


import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table (name = "employe")
public class Employe implements Serializable {

    @Id
    @Column (name = "CIN")
    @Pattern(regexp = "\\d{8}", message = "Le champ de CIN doit contenir exactement 8 chiffres")
    private String CIN;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "email")
    private String email;

    @Column(name = "hire_date")
    private Date hireDate;

    @Column(name = "isActive")
    private Boolean isActive;

    @Column(name = "salaire")
    private Float salaire;

    @Column(name = "position")
    private String position;

    @ManyToOne
    @JoinTable(name = "departement_employes")
    private Departement departement;

    @OneToMany (cascade = CascadeType.ALL, mappedBy = "employe")
    private List<Conge> conges;





}
