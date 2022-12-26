package com.demounittest.repository;

import com.demounittest.model.Categories;
import com.demounittest.model.Outcomes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ICategoryRepository extends JpaRepository<Categories, Long> {
    Optional<Categories> findByName(String name);
    Iterable<Categories> findAllByOutComes(Outcomes outcome);
}
