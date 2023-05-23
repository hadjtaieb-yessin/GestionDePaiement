package com.bezkoder.spring.security.postgresql.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@Entity
@Table (name = "departement")
public class Departement implements Serializable {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Long id;

    @NotEmpty (message = "name must not be empty")
    @Column (name = "name")
    private String name;

    @NotBlank
    @Size (max = 20)
    @Column (name = "nomDepartement")
    private String nomDepartement;

    @Column (name = "budget")
    private Float budget;

    @ManyToOne
    @JoinTable(name = "entreprise_departements")
    private Entreprise entreprise;



}
