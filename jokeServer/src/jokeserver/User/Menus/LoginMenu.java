/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jokeserver.User.Menus;

/**
 *
 * @author edoua
 */
import jokeserver.User.Model.User;
import java.util.Scanner;
import jokeserver.User.Authentication;

public class LoginMenu {
    private Scanner scanner = new Scanner(System.in);
    private Authentication authentication;
    private User currentUser;

    public LoginMenu() {
        this.authentication = new Authentication();
    }

    public User login() {
        System.out.println("=== Login ===");
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        // Authenticate user and get the User object
        currentUser = authentication.authenticateUser(username, password);
        if (currentUser != null) {
            System.out.println("Login successful. Welcome, " + username + "!");
        } else {
            System.out.println("Login failed. Invalid username or password.");
        }
        return currentUser;
    }

    public User getCurrentUser() {
        return currentUser;
    }
}
