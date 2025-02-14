/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jokeserver.User.Menus;

import jokeserver.User.Model.User;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import jokeserver.Category.Controller.categoryServImpl;
import jokeserver.Category.DAO.categoryDAO;
import jokeserver.Category.Model.category;
import jokeserver.Jokes.Model.Jokes;
import jokeserver.Jokes.Service.JokeServiceImpl;

/**
 *
 * @author edoua
 */
public class UserMenu {
    private User currentUser;
    private JokeServiceImpl jsi;
    private categoryServImpl csi;
    private categoryDAO cdo;
    public UserMenu(User currentUser) {
                this.currentUser = currentUser;
        this.jsi = new JokeServiceImpl();
        
        this.csi = new categoryServImpl(cdo);
        
    }
    public void displayMenu() {
        System.out.println("Welcome, " + currentUser.getUsername() + "!");
        System.out.println("User Menu:");
        System.out.println("1. View Jokes");
        System.out.println("2. Create Joke");
        System.out.println("3. view jokes by category");
        System.out.println("4. Logout");
    }

    public void performAction(String choice) {
        switch (choice) {
            case "1":
                viewJokes();
                break;
            case "2":
                createJoke();
                break;
            case "3":
                viewJokesByCategory();
                break;
            case "4":
                logout();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }



    private void viewJokes() {
            ArrayList<Jokes> jokes = (ArrayList<Jokes>) jsi.getAllJokes();
    System.out.println("All Jokes:");
    for (Jokes joke : jokes) {
        System.out.println(joke.getJokeText());
    }
    }

    private void createJoke() {
        Scanner scan=new Scanner(System.in);
        System.out.println();
        String jokeTxt=scan.nextLine();
        Date d= new Date();
        int userid=currentUser.getId();
                ArrayList<category> categories = (ArrayList<category>) csi.getCategories();
        
        
        System.out.println("Available categories:");
        for (category cate : categories) {
            System.out.println(cate.getCategoryID() + ". " + cate.getCategoryName());
        }
        
        
        System.out.println("Enter the IDs of the categories for this joke (comma-separated):");
        String categoryIdsInput = scan.nextLine();
        
        String[] categoryIdStrs = categoryIdsInput.split(",");
        ArrayList<Integer> categoryIds = new ArrayList<>();
        for (String categoryIdStr : categoryIdStrs) {
            try {
                int categoryId = Integer.parseInt(categoryIdStr.trim());
                categoryIds.add(categoryId);
            } catch (NumberFormatException e) {
                System.out.println("Invalid category ID: " + categoryIdStr);
            }
        }
        boolean jokeCreated =jsi.createJoke(jokeTxt, userid, d, categoryIds);
                if (jokeCreated) {
            System.out.println("Joke created successfully!");
        } else {
            System.out.println("Failed to create joke. Please try again.");
        }
    }

    public void logout() {
        System.out.println("Logging out...");
        
    }

    private void viewJokesByCategory() {
    Scanner scanner = new Scanner(System.in);
    
    // Display available categories
    System.out.println("Available categories:");
    List<category> categories = csi.getCategories();
    for (category cate : categories) {
        System.out.println(cate.getCategoryName());
    }
    
    
    System.out.println("Enter the name of the category:");
    String categoryName = scanner.nextLine();
    
    
    List<Jokes> jokes = jsi.getJokesByCategory(categoryName);
    
    
    System.out.println("Jokes in category " + categoryName + ":");
    for (Jokes joke : jokes) {
        System.out.println(joke.getJokeText());
    }
    }

}

