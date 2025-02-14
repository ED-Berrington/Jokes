/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jokeserver.User.Menus;

import jokeserver.User.Model.User;
import java.util.ArrayList;
import java.util.Scanner;
import jokeserver.Category.Controller.categoryServImpl;
import jokeserver.Category.DAO.categoryDAO;
import jokeserver.Category.Model.category;
import jokeserver.Jokes.Model.Jokes;
import jokeserver.Jokes.Service.JokeServiceImpl;
import jokeserver.User.Services.MemberServiceImpl;

/**
 *
 * @author edoua
 */
public class AdminMenu {
        private User currentUser;
    private JokeServiceImpl jsi=new JokeServiceImpl();
    
    private categoryDAO cdo=new categoryDAO();
    private categoryServImpl csi=new categoryServImpl(cdo);
    private MemberServiceImpl msi=new MemberServiceImpl();
    Scanner scan= new Scanner(System.in);
    
    public AdminMenu(User currentUser) {
        this.currentUser = currentUser;
    }

    public void displayMenu() {
        System.out.println("Welcome, Admin " + currentUser.getUsername() + "!");
        System.out.println("Admin Menu:");
        System.out.println("1. View All Jokes");
        System.out.println("2. Verify Joke");
        System.out.println("3. Delete Joke");
        System.out.println("4. View Users");
        System.out.println("5. Delete User");
        System.out.println("6. Logout");
        System.out.println("7. Add Category");
        System.out.println("8. Remove Category");
        System.out.println("9. View categories");
        
    }

    public void performAction(String choice) {
        switch (choice) {
            case "1":
                viewAllJokes();
                break;
            case "2":
                verifyJoke();
                break;
            case "3":
                deleteJoke();
                break;
            case "4":
                viewUsers();
                break;
            case "5":
                deleteUser();
                break;
            case "6":
                System.out.println("Logging out...");
                
                break;
                
            case "7":
                addCategory();
                break;
            case "8":
            removeCategory();
            break;
            case "9":
                viewCategories();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private void viewAllJokes() {
    ArrayList<Jokes> jokes = (ArrayList<Jokes>) jsi.getAllJokesIncludingUnverified();
    System.out.println("All Jokes:");
    for (Jokes joke : jokes) {
        boolean verified=joke.getVerified()>0;
        System.out.println("ID: "+joke.getId()+" Joke: "+joke.getJokeText()+"  Verified: "+verified);
    }
    }

    private void verifyJoke() {
        try{
        System.out.println("Enter the ID of the joke to verify:");
        int option = Integer.parseInt(scan.nextLine());
        boolean verified = jsi.verifyJoke(option);
        if (verified) {
            System.out.println("Joke verified successfully!");
        } else {
            System.out.println("Failed to verify joke. Please try again.");
        }
    } catch (NumberFormatException e) {
        System.out.println("Invalid input. Please enter a valid integer.");
    }
    }


    

    private void viewUsers() {
        ArrayList<User> users = msi.getAllMembers();
    
    if (users.isEmpty()) {
        System.out.println("No users found.");
    } else {
        System.out.println("List of Users:");
        for (User user : users) {
            System.out.println("ID: " + user.getId() + ", Username: " + user.getUsername() + ", Role: " + user.getRole());
        }
    }
    }

    private void deleteUser() {
            System.out.println("Enter the ID of the user to delete:");
    int userId = Integer.parseInt(scan.nextLine());
    
    boolean deleted = msi.deleteMember(userId);
    
    if (deleted) {
        System.out.println("User deleted successfully.");
    } else {
        System.out.println("Failed to delete user. Please try again.");
    }
    }

    public void logout() {
        System.out.println("Logging out...");
    }

    private void deleteJoke() {
            try {
        System.out.println("Enter the ID of the joke to delete:");
        int jokeId = Integer.parseInt(scan.nextLine());
        boolean deleted = jsi.deleteJoke(jokeId);
        if (deleted) {
            System.out.println("Joke deleted successfully!");
        } else {
            System.out.println("Failed to delete joke. Please try again.");
        }
    } catch (NumberFormatException e) {
        System.out.println("Invalid input. Please enter a valid integer.");
    }
    }

    private void addCategory() {
                try {
            System.out.println("=== Add Category ===");
            System.out.print("Enter category name: ");
            String name = scan.nextLine();
            System.out.print("Enter category description: ");
            String description = scan.nextLine();

            boolean added = csi.createCategory(name,description);
            if (added) {
                System.out.println("Category added successfully.");
            } else {
                System.out.println("Failed to add category.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    private void removeCategory() {
 try {
            System.out.println("=== Remove Category ===");
            System.out.print("Enter category ID to remove: ");
            int categoryId = Integer.parseInt(scan.nextLine());
            

            boolean removed = csi.deleteCategory(categoryId);
            if (removed) {
                System.out.println("Category removed successfully.");
            } else {
                System.out.println("Failed to remove category.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
    

    private void viewCategories() {
                System.out.println("=== All Categories ===");
        ArrayList<category> categories = (ArrayList<category>) csi.getCategories();
        for (category category : categories) {
            System.out.println("ID: " + category.getCategoryID() + ", Name: " + category.getCategoryName() + ", Description: " + category.getCategoryDescription());
    }
}
}
