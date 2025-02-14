/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package jokeserver.Category.DAO;

import java.util.List;
import jokeserver.Category.Model.category;

/**
 *
 * @author edoua
 */
public interface cDAO {
        boolean createCategory(category category);
    
    List<category> getCategories();
    List<category> filterCategory(String searchTerm);
    category updateCategory(category category);
    boolean deleteCategory(int categoryId);
}
