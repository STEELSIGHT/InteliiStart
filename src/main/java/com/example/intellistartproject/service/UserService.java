package com.example.intellistartproject.service;

import com.example.intellistartproject.model.Product;
import com.example.intellistartproject.model.User;
import com.example.intellistartproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUser(){
        return userRepository.findAll();
    }
    public Optional<User> getUserById(int id){
        return userRepository.findById(id);
    }
    public void addUser(User user){
        userRepository.save(user);
    }
    public void deleteUserById(int id){
        userRepository.deleteById(id);
    }
}
