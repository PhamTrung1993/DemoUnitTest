package com.demounittest.service.evaluation;

import com.demounittest.model.Coach;
import com.demounittest.model.Evaluations;
import com.demounittest.model.Student;
import com.demounittest.model.Templates;
import com.demounittest.service.GeneralService;

public interface IEvaluationService extends GeneralService<Evaluations> {
    Iterable<Evaluations> findAllByCoach(Coach coach);
    Iterable<Evaluations> findAllByTemplates(Templates templates);
    Iterable<Evaluations> findAllByStudent(Student student);
}
