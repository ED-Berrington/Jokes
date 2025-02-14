/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jokeserver.DBConfig;
import java.sql.*;
/**
 *
 * @author edoua
 */
public class DBConfig {
    private Connection connector;

    public DBConfig() {
        try{
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url="jdbc:mysql://Localhost:3306/Jokesdb?autoSSL=false";
        connector=DriverManager.getConnection(url,"root","root");
        
        }catch(SQLException|ClassNotFoundException e){
        System.out.println("Error with finding class or sql connection");
        }
    }

    public Connection getConnector() {
        return connector;
    }


    
}

