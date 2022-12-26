package com.demounittest.service.role;

import com.demounittest.model.Roles;
import com.demounittest.repository.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService implements IRoleService{
    @Autowired
    private IRoleRepository roleRepository;


    @Override
    public Iterable<Roles> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Optional<Roles> findById(Long id) {
        return roleRepository.findById(id);
    }

    @Override
    public Roles save(Roles role) {
        return roleRepository.save(role);
    }

    @Override
    public void remove(Long id) {
        roleRepository.deleteById(id);
    }
}
