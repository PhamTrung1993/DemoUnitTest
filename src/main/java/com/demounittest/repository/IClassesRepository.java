package com.demounittest.repository;

import com.demounittest.model.Classes;
import com.demounittest.model.Coach;
import com.demounittest.model.Programs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IClassesRepository extends JpaRepository<Classes, Long> {
    Iterable<Classes> findAllByCoach(Coach coach);

    Iterable<Classes> findAllByPrograms(Programs programs);
}
