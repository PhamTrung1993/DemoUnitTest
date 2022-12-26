package com.demounittest.repository;

import com.demounittest.model.Programs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface IProgramRepository extends JpaRepository<Programs, Long> {
    Optional<Programs> findByName(String name);
}
