package com.algonquin.cookbook.dao;

import com.algonquin.cookbook.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {

    public void addUser(User user) {
        try {
            String query = "insert into cookbook.user (email, username, password, isAccountVerified) values (?,?,?,?)";
            try (Connection connection = DBConnection.getConnectionToDatabase();
                 PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(2, user.getEmail());
                statement.setString(1, user.getUsername());
                statement.setString(3, user.getPassword());
                statement.setBoolean(4, user.getAccountVerified());
                statement.executeUpdate();
                System.out.println("User added to database.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User findUser(String email) {
        User user = null;
        try {
            String query = "select * from cookbook.user where email = '" + email + "'";
            try (Connection connection = DBConnection.getConnectionToDatabase();
                 PreparedStatement statement = connection.prepareStatement(query);
                 ResultSet set = statement.executeQuery(query)) {
                while (set.next()) {
                    user = new User();
                    user.setEmail(set.getString("email"));
                    user.setUsername(set.getString("username"));
                    user.setPassword(set.getString("password"));
                    user.setAccountVerified(set.getBoolean("isAccountVerified"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public User findUser(String email, String password) {
        User user = null;
        try {
            String query = "select * from cookbook.user where email = '" + email + "and password = " + password + " '";
            try (Connection connection = DBConnection.getConnectionToDatabase();
                 PreparedStatement statement = connection.prepareStatement(query);
                 ResultSet set = statement.executeQuery(query)) {
                while (set.next()) {
                    user = new User();
                    user.setEmail(set.getString("email"));
                    user.setUsername(set.getString("username"));
                    user.setPassword(set.getString("password"));
                    user.setAccountVerified(set.getBoolean("isAccountVerified"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public Boolean updateUser(String userEmail) {
        try {
            String query = "update cookbook.user set isAccountVerified = 1 where email = ?";
            try (Connection connection = DBConnection.getConnectionToDatabase();
                 PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, userEmail);
                int i = statement.executeUpdate();
                if (i == 1) {
                    System.out.println("Account Successfully Verified.");
                    return true;
                } else {
                    return false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
