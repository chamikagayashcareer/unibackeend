package com.uni.unibakeend.services;

import org.springframework.stereotype.Service;

import com.uni.unibakeend.repositories.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void addUser() {

    }
}
