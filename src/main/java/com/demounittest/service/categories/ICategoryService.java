package com.demounittest.service.categories;

import com.demounittest.model.Categories;
import com.demounittest.model.Outcomes;
import com.demounittest.service.GeneralService;

import java.util.Optional;

public interface ICategoryService extends GeneralService<Categories> {
    Optional<Categories> findByName(String name);
    Iterable<Categories> findAllByOutComes(Outcomes outcome);
}
