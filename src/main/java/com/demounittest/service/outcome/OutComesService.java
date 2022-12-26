package com.demounittest.service.outcome;

import com.demounittest.model.Outcomes;
import com.demounittest.model.Roles;
import com.demounittest.repository.IOutcomesRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OutComesService implements IOutComesService {
    @Autowired
    private IOutcomesRepository outcomesRepository;

    public OutComesService() {
    }

    public OutComesService(IOutcomesRepository outcomesRepository){
        this.outcomesRepository = outcomesRepository;
    }

    @Override
    public Iterable<Outcomes> findAll() {
        return outcomesRepository.findAll();
    }

    @Override
    public Optional<Outcomes> findById(Long id) {
        return outcomesRepository.findById(id);
    }

    @Override
    public Outcomes save(Outcomes outcomes) {
        return outcomesRepository.save(outcomes);
    }

    @Override
    public void remove(Long id) {
        outcomesRepository.deleteById(id);
    }

    @Override
    public Optional<Outcomes> findByTitle(String title) {
        return outcomesRepository.findByTitle(title);
    }
}
