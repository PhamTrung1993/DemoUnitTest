package com.demounittest.service.skills;

import com.demounittest.model.Categories;
import com.demounittest.model.Skills;
import com.demounittest.service.GeneralService;

public interface ISkillsService extends GeneralService<Skills> {
    Iterable<Skills> findAllByCategories(Categories category);
}
