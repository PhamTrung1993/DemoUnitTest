package com.demounittest.repository;

import com.demounittest.model.Coach;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICoachRepository extends JpaRepository<Coach, Long> {
    Coach findByEmail(String email);
}
