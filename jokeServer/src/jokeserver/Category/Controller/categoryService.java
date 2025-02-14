/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package jokeserver.Category.Controller;

import java.util.List;
import jokeserver.Category.Model.category;

/**
 *
 * @author edoua
 */
public interface categoryService {
    boolean deleteCategory(int categoryId);
    category updateCategory(int id,String category, String description);
    List<category> filterCategory(String searchTerm);
    List<category> getCategories();
    boolean createCategory(String category, String description);
}
