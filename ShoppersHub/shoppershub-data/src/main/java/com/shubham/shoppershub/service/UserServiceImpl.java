package com.shubham.shoppershub.service;

import com.shubham.shoppershub.dao.UserRepository;
import com.shubham.shoppershub.entity.Role;
import com.shubham.shoppershub.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;

//    @Autowired
//    public UserServiceImpl(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }


    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleService roleService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
    }

    @Override
    public User saveUser(User user) {
        Role role = roleService.getRoleByName("USER");
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);
        return userRepository.save(user);
    }

    @Override
    public User saveAdminUser(User user) {
        Role role = roleService.getRoleByName("ADMIN");
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);
        return userRepository.save(user);
    }

    @Override
    public void deleteUserById(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User getUserByUsername(String username) {

        Optional<User> userOptional = Optional.ofNullable(userRepository.getUserByUsername(username));

        if (userOptional.isEmpty()) {
            throw new RuntimeException("User with " + username + " not found!!!");
        }

        User foundUser = userOptional.get();
        return foundUser;
    }

    @Override
    public User getUserById(long id) {

        Optional<User> userOptional = Optional.ofNullable(userRepository.getUserById(id));

        if (userOptional.isEmpty()) {
            throw new RuntimeException("User with " + id + " not found!!!");
        }

        User foundUser = userOptional.get();
        return foundUser;
    }
}
