package com.pruebaEKT.demo.service.impl;

import com.pruebaEKT.demo.service.UserService;
import com.pruebaEKT.demo.model.User;
import com.pruebaEKT.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(Integer id) {
        return userRepository.findById(id);
    }

    @Override
    public User updateUser(Integer id, User user) {
        Optional<User> existing = userRepository.findById(id);
        if (existing.isPresent()) {
            User updated = existing.get();
            updated.setName(user.getName());
            updated.setApellido(user.getApellido());
            updated.setAge(user.getAge());
            return userRepository.save(updated);
        }
        return null;
    }

    @Override
    public boolean deleteUser(Integer id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
