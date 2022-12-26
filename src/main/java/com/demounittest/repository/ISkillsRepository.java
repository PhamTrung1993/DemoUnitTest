package com.demounittest.repository;

import com.demounittest.model.Categories;
import com.demounittest.model.Skills;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISkillsRepository extends JpaRepository<Skills, Long> {
    Iterable<Skills> findAllByCategories(Categories category);
}
