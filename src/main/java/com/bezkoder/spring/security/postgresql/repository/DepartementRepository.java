package com.bezkoder.spring.security.postgresql.repository;


import com.bezkoder.spring.security.postgresql.models.Departement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartementRepository extends JpaRepository <Departement,Long> {

    Departement findByName(String name);


}
