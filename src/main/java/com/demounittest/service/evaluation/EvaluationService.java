package com.demounittest.service.evaluation;

import com.demounittest.model.*;
import com.demounittest.repository.IEvaluationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EvaluationService implements IEvaluationService{
    @Autowired
    private IEvaluationsRepository evaluationsRepository;

    @Override
    public Iterable<Evaluations> findAll() {
        return evaluationsRepository.findAll();
    }

    @Override
    public Optional<Evaluations> findById(Long id) {
        return evaluationsRepository.findById(id);
    }

    @Override
    public Evaluations save(Evaluations evaluations) {
        return evaluationsRepository.save(evaluations);
    }

    @Override
    public void remove(Long id) {
        evaluationsRepository.deleteById(id);
    }

    @Override
    public Iterable<Evaluations> findAllByCoach(Coach coach) {
        return evaluationsRepository.findAllByCoach(coach);
    }

    @Override
    public Iterable<Evaluations> findAllByTemplates(Templates templates) {
        return evaluationsRepository.findAllByTemplates(templates);
    }

    @Override
    public Iterable<Evaluations> findAllByStudent(Student student) {
        return evaluationsRepository.findAllByStudent(student);
    }
}
