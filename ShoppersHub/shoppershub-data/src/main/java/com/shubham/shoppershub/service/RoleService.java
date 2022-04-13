package com.shubham.shoppershub.service;

import com.shubham.shoppershub.entity.Role;

public interface RoleService {
    public Role saveRole(Role role);
    public void deletedRoleById(int id);
    public Role getRoleById(int id);
    public Role getRoleByName(String name);
}
