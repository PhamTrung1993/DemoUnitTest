package com.demounittest.service.template;

import com.demounittest.model.Templates;
import com.demounittest.repository.ITemplatesRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TemplateService implements ITemplateService {
    @Autowired
    private ITemplatesRepository templatesRepository;

    @Override
    public Iterable<Templates> findAll() {
        return templatesRepository.findAll();
    }

    @Override
    public Optional<Templates> findById(Long id) {
        return templatesRepository.findById(id);
    }

    @Override
    public Templates save(Templates templates) {
        return templatesRepository.save(templates);
    }

    @Override
    public void remove(Long id) {
        templatesRepository.deleteById(id);
    }
}
