package com.example.command_web_service;

import com.example.command_web_service.persist.UserRepository;

import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

@WebService(endpointInterface = "com.example.command_web_service.CommandWs")
public class CommandWsImpl implements CommandWs {
    private final UserRepository userRepository = new UserRepository();

    @Override
    public void insertUser(@WebParam(name = "user") String user,
                           @WebParam(name = "group") String group,
                           @WebParam(name = "role") String role) {
        userRepository.insert(user, group, role);
    }

    @Override
    public void updateUser(@WebParam(name = "user") String user,
                           @WebParam(name = "group") String group,
                           @WebParam(name = "role") String role) {
        userRepository.update(user, group, role);
    }

    @Override
    public void deleteUser(@WebParam(name = "user") String user) {
        userRepository.delete(user);
    }

    @Override
    public List<String> getUsersNamesByRoles(@WebParam(name = "role") String role) {
        return userRepository.getUsersNamesByRoles(role);
    }
}
