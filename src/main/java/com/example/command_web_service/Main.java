package com.example.command_web_service;

import com.example.command_web_service.persist.UserRepository;

public class Main {
    public static void main(String[] args) {
        UserRepository userRepository = new UserRepository();
        System.out.println(userRepository.getUsersNamesByRoles("1"));
    }
}
