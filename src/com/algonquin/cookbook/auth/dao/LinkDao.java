package com.algonquin.cookbook.dao;

import com.algonquin.cookbook.entity.Link;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LinkDao {

    public void addLink(Link link) {
        try {
            String query = "insert into cookbook.link (userEmail, uniqueString, linkType, isLinkUsed) values (?,?,?,?)";
            try (Connection connection = DBConnection.getConnectionToDatabase();
                 PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, link.getUserEmail());
                statement.setString(2, link.getUniqueString());
                statement.setString(3, link.getLinkType().name());
                statement.setBoolean(4, link.getLinkUsed());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Boolean verifyLink(String uniqueString) {
        String query = "select * from cookbook.link where uniqueString = '" + uniqueString + "'";
        try (Connection connection = DBConnection.getConnectionToDatabase();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet set = statement.executeQuery(query)) {

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Boolean updateLink(String email) {
        try {
            String query = "update cookbook.link set isLinkUsed = 1 where email = ?";
            try (Connection connection = DBConnection.getConnectionToDatabase();
                 PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, email);
                int i = statement.executeUpdate();
                if (i == 1) {
                    System.out.println("Set isLinkUsed = true");
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
