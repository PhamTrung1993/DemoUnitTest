package com.demounittest.service.evaluationdetails;

import com.demounittest.model.EvaluationDetails;
import com.demounittest.model.Evaluations;
import com.demounittest.service.GeneralService;

public interface IEvaluationDetailService extends GeneralService<EvaluationDetails> {
    Iterable<EvaluationDetails> findAllByEvaluation(Evaluations evaluation);
}
