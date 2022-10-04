package com.example.command_web_service;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.util.List;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface CommandWs {

    @WebMethod
    void insertUser(String user, String group, String role);

//    List<String> getIdChatByRole(String roleName);

//    List<String> getListOfUsers();

    @WebMethod
    void updateUser(String user, String group, String role);

    @WebMethod
    void deleteUser(String user);

    @WebMethod
    List<String> getUserNameListByRoleName(String roleName);

    @WebMethod
    void insertGroup(String groupName);

    @WebMethod
    void updateGroup(String oldGroupName, String newGroupName);

    //удаляет всю группу со всеми юзерами каскадно!!!!!
    @WebMethod
    void deleteGroup(String groupName);

    @WebMethod
    List<String> getGroups();

    @WebMethod
    List<String> getListOfGroupUsers(String groupName);

    @WebMethod
    String getRoleNameByUserName(String userName);

//    void putChatIdByUserName(String chatId, String userName);
}
