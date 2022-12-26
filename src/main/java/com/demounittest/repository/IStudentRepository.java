package com.demounittest.repository;

import com.demounittest.model.Classes;
import com.demounittest.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IStudentRepository extends JpaRepository<Student, Long> {
    Iterable<Student> findAllByClasses(Classes classes);
}
