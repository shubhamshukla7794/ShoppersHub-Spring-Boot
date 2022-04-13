package com.shubham.shoppershub.service;

import com.shubham.shoppershub.dao.RoleRepository;
import com.shubham.shoppershub.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void deletedRoleById(int id) {
        roleRepository.deleteById(id);
    }

    @Override
    public Role getRoleById(int id) {

        Optional<Role> roleOptional = roleRepository.findById(id);

        if (roleOptional.isEmpty()) {
            throw new RuntimeException("Role with " + id + " not found!!!");
        }

        Role role = roleOptional.get();
        return role;
    }

    @Override
    public Role getRoleByName(String name) {
        Optional<Role> roleOptional = Optional.ofNullable(roleRepository.getRoleByName(name));

        if (roleOptional.isEmpty()) {
            throw new RuntimeException("Role with " + name + " not found!!!");
        }

        Role role = roleOptional.get();
        return role;
    }
}
