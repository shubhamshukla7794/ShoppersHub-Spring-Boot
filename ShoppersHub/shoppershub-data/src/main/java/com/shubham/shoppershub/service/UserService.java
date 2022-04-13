package com.shubham.shoppershub.service;

import com.shubham.shoppershub.entity.User;

public interface UserService {
    public User saveUser(User user);
    public User saveAdminUser(User user);
    public void deleteUserById(long id);
    public User getUserByUsername(String username);
    public User getUserById(long id);
}
