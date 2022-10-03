package com.example.command_web_service.persist;

public class User {
    private String userName;
    private Group group_id;
    private Role role_id;

    public User(String userName, Group group_id, Role role_id) {
        this.userName = userName;
        this.group_id = group_id;
        this.role_id = role_id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Group getGroup_id() {
        return group_id;
    }

    public void setGroup_id(Group group_id) {
        this.group_id = group_id;
    }

    public Role getRole_id() {
        return role_id;
    }

    public void setRole_id(Role role_id) {
        this.role_id = role_id;
    }
}
