/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jokeserver.User;

import jokeserver.User.Model.User;
import java.sql.*;
import jokeserver.DBConfig.DBConfig;
/**
 *
 * @author edoua
 */
public class Authentication {
    
    private DBConfig dbConfig;
    
    public Authentication() {
        this.dbConfig = new DBConfig();
    }
    
    public User authenticateUser(String username, String password) {
        try (Connection connection = dbConfig.getConnector();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?")) {

            statement.setString(1, username);
            statement.setString(2, password);
            
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String role = resultSet.getString("role"); 
                    int userId = resultSet.getInt("userID"); 
                    
                    
                    return new User(userId, username, password, role);
                } else {
                    
                    return null;
                }
            }
        } catch (SQLException e) {
            System.out.println("failed to autenticate");
            return null;
        }
    }
}
