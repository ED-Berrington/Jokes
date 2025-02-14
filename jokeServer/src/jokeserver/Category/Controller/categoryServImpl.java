/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jokeserver.Category.Controller;

import java.util.List;
import jokeserver.Category.DAO.categoryDAO;
import jokeserver.Category.Model.category;

/**
 *
 * @author edoua
 */
public class categoryServImpl implements categoryService{
categoryDAO cat;

    public categoryServImpl(categoryDAO cat) {
        this.cat = cat;
    }


    @Override
    public boolean deleteCategory(int categoryId) {
        return cat.deleteCategory(categoryId);
    }

    @Override
    public category updateCategory(int id,String category, String description) {
        category categ=new category(id,category,description);
        return cat.updateCategory(categ);
    }

    @Override
    public List<category> filterCategory(String searchTerm) {
        return cat.filterCategory(searchTerm);
    }

    @Override
    public List<category> getCategories() {
        return cat.getCategories();
    }

    @Override
    public boolean createCategory(String category, String description) {
        category cate=new category(category,description);
        return cat.createCategory(cate);
    }
    
}
