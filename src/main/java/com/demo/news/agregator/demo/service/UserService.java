package com.demo.news.agregator.demo.service;

import com.demo.news.agregator.demo.model.News;
import com.demo.news.agregator.demo.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {
    public void save(User user);
    public void addUser(User user);

    public boolean isExist(String name);

    public List<User> getAllUsers();

    Optional<User> getUserByName(String name);
}
