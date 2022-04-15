package com.shubham.shoppershub.service;

import com.shubham.shoppershub.entity.User;

public interface UserService {
    public User saveUser(User user);
    public User saveAdminUser(User user);
    public void deleteUserById(long id);
    public User getUserByEmail(String email);
    public User getUserById(long id);
}
