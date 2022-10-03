package com.example.command_web_service.persist;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private final int defaultValue = Integer.MAX_VALUE;
    private static Connection connection;
    private PreparedStatement preparedStatement;

    static {
        try {
            Class.forName("org.postgresql.Driver");
            //для localhost
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/habrdb", "user", "pass");
            //для облачного сервера
//            connection = DriverManager.getConnection("jdbc:postgresql://185.46.11.164/admin", "admin", "aston");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void insert(String user_name, String group_id, String role_id) {
        try {
            connection.setAutoCommit(false);

            int groupId = getGroupId(group_id);
            if (groupId == defaultValue) return;

            int roleId = getRoleId(role_id);
            if (roleId == defaultValue) return;

            preparedStatement = connection
                    .prepareStatement("INSERT INTO users(user_name, group_id, role_id) VALUES (?, ?, ?)");
            preparedStatement.setString(1, user_name);
            preparedStatement.setInt(2, groupId);
            preparedStatement.setInt(3, roleId);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void update(String user_name, String group_id, String role_id) {
        try {
            connection.setAutoCommit(false);

            int groupId = getGroupId(group_id);
            if (groupId == defaultValue) return;

            int roleId = getRoleId(role_id);
            if (roleId == defaultValue) return;

            preparedStatement = connection
                    .prepareStatement("UPDATE users SET group_id = ?, role_id = ? WHERE user_name = ?");
            preparedStatement.setInt(1, groupId);
            preparedStatement.setInt(2, roleId);
            preparedStatement.setString(3, user_name);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void delete(String user_name) {
        try {
            preparedStatement = connection.prepareStatement("DELETE FROM users WHERE user_name = ?");
            preparedStatement.setString(1, user_name);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<String> getUsersNamesByRoles(String role) {
        List<String> usersNamesList = new ArrayList<>();
        try {
            connection.setAutoCommit(false);

            int roleId = getRoleId(role);
            if (roleId == defaultValue) return usersNamesList;

            preparedStatement = connection.prepareStatement("SELECT user_name FROM users WHERE role_id = ?");
            preparedStatement.setInt(1, roleId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                usersNamesList.add(rs.getString(1));
            }
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return usersNamesList;
    }

    private int getGroupId(String group) throws SQLException {
        ResultSet rs;
        int groupId = defaultValue;
        preparedStatement = connection
                .prepareStatement("SELECT id FROM groups WHERE name = ?");
        preparedStatement.setString(1, group);
        rs = preparedStatement.executeQuery();
        while (rs.next()) {
            groupId = rs.getInt(1);
            if (groupId == defaultValue) {
                connection.commit();
            }
        }
        return groupId;
    }

    private int getRoleId(String role) throws SQLException {
        ResultSet rs;
        int roleId = defaultValue;
        preparedStatement = connection
                .prepareStatement("SELECT id FROM roles WHERE name = ?");
        preparedStatement.setString(1, role);
        rs = preparedStatement.executeQuery();
        while (rs.next()) {
            roleId = rs.getInt(1);
            if (roleId == defaultValue) {
                connection.commit();
            }
        }
        return roleId;
    }
}
