package com.demounittest.service.classes;

import com.demounittest.model.Classes;
import com.demounittest.model.Coach;
import com.demounittest.model.Programs;
import com.demounittest.service.GeneralService;

public interface IClassesService extends GeneralService<Classes> {
    Iterable<Classes> findAllByCoach(Coach coach);
    Iterable<Classes> findAllByPrograms(Programs programs);
}
