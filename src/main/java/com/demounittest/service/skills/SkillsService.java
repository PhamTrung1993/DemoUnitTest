package com.demounittest.service.skills;

import com.demounittest.model.Categories;
import com.demounittest.model.Skills;
import com.demounittest.repository.ISkillsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SkillsService implements ISkillsService{
    @Autowired
    private ISkillsRepository skillsRepository;

    @Override
    public Iterable<Skills> findAll() {
        return skillsRepository.findAll();
    }

    @Override
    public Optional<Skills> findById(Long id) {
        return skillsRepository.findById(id);
    }

    @Override
    public Skills save(Skills skills) {
        return skillsRepository.save(skills);
    }

    @Override
    public void remove(Long id) {
        skillsRepository.deleteById(id);
    }

    @Override
    public Iterable<Skills> findAllByCategories(Categories category) {
        return skillsRepository.findAllByCategories(category);
    }
}
