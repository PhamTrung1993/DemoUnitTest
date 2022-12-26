package com.demounittest.service.programs;

import com.demounittest.model.Programs;
import com.demounittest.model.Roles;
import com.demounittest.repository.IProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProgramsService implements IProgramsService {
    @Autowired
    private IProgramRepository programRepository;

    @Override
    public Iterable<Programs> findAll() {
        return programRepository.findAll();
    }

    @Override
    public Optional<Programs> findById(Long id) {
        return programRepository.findById(id);
    }

    @Override
    public Programs save(Programs programs) {
        return programRepository.save(programs);
    }

    @Override
    public void remove(Long id) {
        programRepository.deleteById(id);
    }

    @Override
    public Optional<Programs> findByName(String name) {
        return programRepository.findByName(name);
    }
}
