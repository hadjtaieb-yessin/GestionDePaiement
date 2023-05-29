package com.bezkoder.spring.security.postgresql.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Data
@Getter
@Setter
@Entity
@Table (name = "Depense")
public class Depense implements Serializable {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Long id;

    @NotEmpty (message = "description must not be empty")
    @Size (max = 100)
    @Column (name = "description")
    private String description;

    @Column (name = "somme")
    private Float somme;

    @Column (name = "date")
    private Date date;

    @Enumerated (EnumType.STRING)
    @Column
    private EDepense type;




}
