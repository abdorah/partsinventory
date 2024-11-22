package com.partsinventory.service;

import com.partsinventory.helper.DbConnection;
import com.partsinventory.model.Users;

import javax.naming.AuthenticationException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginService {

    public static Users checkCredentials(String user, String pass) throws SQLException, AuthenticationException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = DbConnection.load("LOGIN");
        try {
            preparedStatement = DbConnection.getConnection().prepareStatement(query);
            preparedStatement.setString(1, user);
            preparedStatement.setString(2, pass);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new Users(resultSet.getInt("id"), resultSet.getString("username"), resultSet.getString("role"));

            }
        } catch (Exception e) {
            throw new AuthenticationException("Invalid credentials");
        } finally {
            preparedStatement.close();
            resultSet.close();
        }
        return null;
    }
}