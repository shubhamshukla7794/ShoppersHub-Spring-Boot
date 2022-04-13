package com.shubham.shoppershub.dao;

import com.shubham.shoppershub.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.username = ?1")
    public User getUserByUsername(String username);

    @Query("SELECT u FROM User u WHERE u.id = ?1")
    public User getUserById(Long userId);
}
