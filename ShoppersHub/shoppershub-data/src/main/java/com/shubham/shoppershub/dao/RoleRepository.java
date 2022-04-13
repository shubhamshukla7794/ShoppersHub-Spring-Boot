package com.shubham.shoppershub.dao;

import com.shubham.shoppershub.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    @Query("SELECT r FROM Role r WHERE r.name=?1")
    public Role getRoleByName(String name);
}
