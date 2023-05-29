package com.bezkoder.spring.security.postgresql.repository;


import com.bezkoder.spring.security.postgresql.models.Employe;
import com.bezkoder.spring.security.postgresql.models.PaiementPersonnel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaiementPersonnelRepository extends JpaRepository<PaiementPersonnel,Long> {

    Optional<PaiementPersonnel> findByReference(Long reference);


}
