package com.demounittest.repository;

import com.demounittest.model.Outcomes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IOutcomesRepository extends JpaRepository<Outcomes, Long> {
    Optional<Outcomes> findByTitle(String title);
}
