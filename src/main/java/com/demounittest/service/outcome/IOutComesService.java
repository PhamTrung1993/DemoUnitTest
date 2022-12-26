package com.demounittest.service.outcome;

import com.demounittest.model.Outcomes;
import com.demounittest.service.GeneralService;

import java.util.Optional;

public interface IOutComesService extends GeneralService<Outcomes> {
    Optional<Outcomes> findByTitle(String title);
}
