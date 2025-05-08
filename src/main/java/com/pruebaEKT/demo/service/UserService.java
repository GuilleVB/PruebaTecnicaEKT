package com.pruebaEKT.demo.service;

import com.pruebaEKT.demo.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService   {
    User saveUser(User user);
    List<User> getAllUsers();
    Optional<User> getUserById(Integer id);
    User updateUser(Integer id, User user);
    boolean deleteUser(Integer id);
}
