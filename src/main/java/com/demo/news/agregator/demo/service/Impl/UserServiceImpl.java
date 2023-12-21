package com.demo.news.agregator.demo.service.Impl;

import com.demo.news.agregator.demo.model.User;
import com.demo.news.agregator.demo.repository.UserRepository;
import com.demo.news.agregator.demo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository repository;
    private PasswordEncoder passwordEncoder;

    @Override
    public void save(User user) {
        repository.save(user);
    }

    @Override
    public boolean isExist(String name) {
        List<User> allUser = repository.findAll();
        for (User u: allUser) {
            if (u.getName().equals(name)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        repository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return repository.findAll();
    }

    @Override
    public Optional<User> getUserByName(String name) {
        return repository.findByName(name);
    }

}
