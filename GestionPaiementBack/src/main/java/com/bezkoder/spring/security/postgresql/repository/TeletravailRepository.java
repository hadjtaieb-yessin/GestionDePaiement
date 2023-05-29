package com.bezkoder.spring.security.postgresql.repository;


import com.bezkoder.spring.security.postgresql.models.Conge;
import com.bezkoder.spring.security.postgresql.models.Teletravail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeletravailRepository extends JpaRepository <Teletravail,Long> {

    Optional<Teletravail> findById(Long id);

}
