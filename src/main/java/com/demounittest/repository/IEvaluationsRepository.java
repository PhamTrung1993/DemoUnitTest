package com.demounittest.repository;

import com.demounittest.model.Coach;
import com.demounittest.model.Evaluations;
import com.demounittest.model.Student;
import com.demounittest.model.Templates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEvaluationsRepository extends JpaRepository<Evaluations, Long> {
    Iterable<Evaluations> findAllByCoach(Coach coach);
    Iterable<Evaluations> findAllByTemplates(Templates templates);
    Iterable<Evaluations> findAllByStudent(Student student);
}
