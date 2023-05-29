package com.bezkoder.spring.security.postgresql.models;



import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Data
@Getter
@Setter
@Entity
@Table (name="Entreprise")
public class Entreprise implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size (max = 20)
    @NotEmpty (message = "name must not be empty")
    @Column(name="name")
    private String Name;

    @Column (name = "address")
    private String address;

    @Column (name = "phone_number")
    private String phoneNumber;

    @Column (name = "email")
    private String email;

    @OneToMany (cascade = CascadeType.ALL, mappedBy = "entreprise")
    private List<Departement> departements;

    @OneToMany (cascade = CascadeType.ALL, mappedBy = "entreprise")
    private List<User> users;




}
