/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jokeserver.Jokes.DAO;

import java.util.List;

import jokeserver.Jokes.Model.Jokes;
import java.sql.*;
import java.util.ArrayList;
import jokeserver.DBConfig.DBConfig;
/**
 *
 * @author edoua
 */
public class JokeDAO implements jDAO {
DBConfig dbconfig=new DBConfig();
    @Override
public boolean createJokes(Jokes joke, List<Integer> categoryIDs) {
    String insertJokeQuery = "INSERT INTO jokes (text, author_ID, date) VALUES (?, ?, ?)";
    String insertCategoryJokeQuery = "INSERT INTO category_joke (categoryID, jokeID) VALUES (?, ?)";
    
    try (Connection con = dbconfig.getConnector();
         PreparedStatement psInsertJoke = con.prepareStatement(insertJokeQuery, PreparedStatement.RETURN_GENERATED_KEYS);
         PreparedStatement psInsertCategoryJoke = con.prepareStatement(insertCategoryJokeQuery)) {
        
        // Insert joke
        psInsertJoke.setString(1, joke.getJokeText());
        psInsertJoke.setInt(2, joke.getAuthorId());
        psInsertJoke.setDate(3, new java.sql.Date(joke.getDateSelected().getTime()));
        
        int rowsAffected = psInsertJoke.executeUpdate();
        if (rowsAffected == 0) {
            return false; // Failed to insert joke
        }
        
        // Retrieve auto-generated joke ID
        ResultSet rs = psInsertJoke.getGeneratedKeys();
        int jokeID;
        if (rs.next()) {
            jokeID = rs.getInt(1);
        } else {
            return false; // Failed to retrieve joke ID
        }
        
        // Associate joke with categories
        for (int categoryID : categoryIDs) {
            psInsertCategoryJoke.setInt(1, categoryID);
            psInsertCategoryJoke.setInt(2, jokeID);
            psInsertCategoryJoke.executeUpdate();
        }
        
        return true; // Successfully created joke and associated categories
        
    } catch (SQLException e) {
        System.out.println("Error inserting joke into the database");
        return false; // Exception occurred
    }
}

    @Override
    public Jokes getJoke(int JokeID) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Jokes> getJokes() {
    List<Jokes> jokes = new ArrayList<>();
    String query = "SELECT * FROM jokes WHERE verified = 1";

    try (Connection con = dbconfig.getConnector();
         PreparedStatement ps = con.prepareStatement(query);
         ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            int jokeID = rs.getInt("jokeID");
            String jokeText = rs.getString("text");
            int authorID = rs.getInt("author_ID");
            Date dateSelected = rs.getDate("date");

            Jokes joke = new Jokes(jokeText, authorID, dateSelected);
            joke.setId(jokeID);

            jokes.add(joke);
        }
    } catch (SQLException e) {
        System.out.println("Error receiving jokes from database");
    }

    return jokes;
    }

    @Override
    public List<Jokes> getJokesCategory(String searchcategory) {
 List<Jokes> jokes = new ArrayList<>();
    String query = "SELECT * FROM jokes INNER JOIN category_joke ON jokes.jokeID = category_joke.jokeID " +
                   "INNER JOIN category ON category_joke.categoryID = category.categoryID " +
                   "WHERE category.categoryName = ? AND jokes.verified = 1"; // Only verified jokes
    
    try (Connection con = dbconfig.getConnector();
         PreparedStatement ps = con.prepareStatement(query)) {
        
        // Set the category name parameter
        ps.setString(1, searchcategory);
        
        
        ResultSet rs = ps.executeQuery();
        
        // Process the result set
        while (rs.next()) {
            int jokeID = rs.getInt("jokes.jokeID");
            String jokeText = rs.getString("jokes.text");
            int authorID = rs.getInt("jokes.author_ID");
            int verified = rs.getInt("jokes.verified");
            Date dateSelected = rs.getDate("jokes.date");
            
            // Create a Jokes object
            Jokes joke = new Jokes(jokeText, authorID, verified, dateSelected);
            joke.setId(jokeID);
            
            // Add the joke to the list
            jokes.add(joke);
        }
    } catch (SQLException e) {
        System.out.println("Error receiving jokes");
    }
    
    return jokes;
}
    

@Override
public boolean updateJoke(Jokes joke) {
    String query = "UPDATE jokes SET text = ?, author_ID = ?, date = ? WHERE jokeID = ?";
    
    try (Connection con = dbconfig.getConnector();
         PreparedStatement ps = con.prepareStatement(query)) {
        
        // Set the parameters for the prepared statement
        ps.setString(1, joke.getJokeText());
        ps.setInt(2, joke.getAuthorId());
        ps.setDate(3, new java.sql.Date(joke.getDateSelected().getTime()));
        ps.setInt(4, joke.getId());
        
        // Execute the update
        int rowsAffected = ps.executeUpdate();
        
        // Check if the update was successful
        return rowsAffected > 0;
    } catch (SQLException e) {
        System.out.println("Error updating joke");
        return false;
    }
}

    @Override
    public boolean deleteJoke(int jokeID) {
            String query = "DELETE FROM jokes WHERE jokeID = ?";
    
    try (Connection con = dbconfig.getConnector();
         PreparedStatement ps = con.prepareStatement(query)) {
        
        
        ps.setInt(1, jokeID);
        
        
        // Execute the delete operation
        int rowsAffected = ps.executeUpdate();
        
        // Check if the delete was successful
        return rowsAffected > 0;
    } catch (SQLException e) {
        System.out.println("Error deleting jokes");
        return false;
    }
    }

public boolean verifyJoke(int jokeId) {
    String query = "UPDATE jokes SET verified = 1 WHERE jokeID = ?";
    try (Connection con = dbconfig.getConnector();
         PreparedStatement ps = con.prepareStatement(query)) {
        ps.setInt(1, jokeId);
        int rowsAffected = ps.executeUpdate();
        return rowsAffected > 0;
    } catch (SQLException e) {
        System.out.println("Error with verifying joke");
        return false;
    }
}

public List<Jokes> getAllJokesIncludingUnverified() {
    List<Jokes> jokes = new ArrayList<>();
    String query = "SELECT * FROM jokes";
    try (Connection con = dbconfig.getConnector();
         PreparedStatement ps = con.prepareStatement(query);
         ResultSet rs = ps.executeQuery()) {
        while (rs.next()) {
            int jokeID = rs.getInt("jokeID");
            String jokeText = rs.getString("text");
            int authorID = rs.getInt("author_ID");
            int verified = rs.getInt("verified");
            Date dateSelected = rs.getDate("date");
            Jokes joke = new Jokes(jokeText, authorID, verified, dateSelected);
            joke.setId(jokeID);
            jokes.add(joke);
        }
    } catch (SQLException e) {
       System.out.println("Error with receiving jokes");
    }
    return jokes;
}
}
