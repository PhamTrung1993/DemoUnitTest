package com.demounittest.service.programs;

import com.demounittest.model.Programs;
import com.demounittest.service.GeneralService;

import java.util.Optional;

public interface IProgramsService extends GeneralService<Programs> {
    Optional<Programs> findByName(String name);
}
