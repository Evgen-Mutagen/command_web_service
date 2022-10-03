package com.example.command_web_service;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.util.List;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface CommandWs {

    //inserts user by userName, group, role
    @WebMethod
    void insertUser(String user, String group, String role);

    //updates user by userName, group, role
    @WebMethod
    void updateUser(String user, String group, String role);

    //deletes user by name
    @WebMethod
    void deleteUser(String user);

    //returns list of usersNames by role
    @WebMethod
    List<String> getUsersNamesByRoles(String role);
}
