/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jokeserver.Category.DAO;

import java.util.List;
import jokeserver.Category.Model.category;
import jokeserver.DBConfig.DBConfig;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author edoua
 */
public class categoryDAO implements cDAO {
private DBConfig dbConfig;

    public categoryDAO() {
        this.dbConfig = new DBConfig();
    }

    @Override
    public boolean createCategory(category category) {
                String query = "INSERT INTO category (categoryName, categoryDescription) VALUES (?, ?)";
        try (Connection con = dbConfig.getConnector();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, category.getCategoryName());
            ps.setString(2, category.getCategoryDescription());
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("failed to create category");
            return false;
        }
    }

    @Override
    public List<category> getCategories() {
                List<category> categories = new ArrayList<>();
        String query = "SELECT * FROM category";
        try (Connection con = dbConfig.getConnector();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                int categoryId = rs.getInt("categoryID");
                String categoryName = rs.getString("categoryName");
                String categoryDescription = rs.getString("categoryDescription");
                category category = new category(categoryId, categoryName, categoryDescription);
                categories.add(category);
            }
        } catch (SQLException e) {
            System.out.println("failed to get categories");
        }
        return categories;
    }

    @Override
    public List<category> filterCategory(String searchTerm) {
                List<category> filteredCategories = new ArrayList<>();
        String query = "SELECT * FROM category WHERE categoryName LIKE ?";
        try (Connection con = dbConfig.getConnector();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, "%" + searchTerm + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int categoryId = rs.getInt("categoryID");
                    String categoryName = rs.getString("categoryName");
                    String categoryDescription = rs.getString("categoryDescription");
                    category category = new category(categoryId, categoryName, categoryDescription);
                    filteredCategories.add(category);
                }
            }
        } catch (SQLException e) {
            System.out.println("Failed to get categories");
        }
        return filteredCategories;
    }

    @Override
    public category updateCategory(category category) {
        String query = "UPDATE category SET categoryName = ?, categoryDescription = ? WHERE categoryID = ?";
        try (Connection con = dbConfig.getConnector();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, category.getCategoryName());
            ps.setString(2, category.getCategoryDescription());
            ps.setInt(3, category.getCategoryID());
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                return category;
            }
        } catch (SQLException e) {
            System.out.println("failed to update categories");
        }
        return null;
    }

    @Override
    public boolean deleteCategory(int categoryId) {
        String query = "DELETE FROM category WHERE categoryID = ?";
        try (Connection con = dbConfig.getConnector();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, categoryId);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Failed to delete category");
            return false;
        }
    }
}
   

