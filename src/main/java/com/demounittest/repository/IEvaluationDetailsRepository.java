package com.demounittest.repository;

import com.demounittest.model.EvaluationDetails;
import com.demounittest.model.Evaluations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEvaluationDetailsRepository extends JpaRepository<EvaluationDetails, Long> {
    Iterable<EvaluationDetails> findAllByEvaluations(Evaluations evaluation);
}
