/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import java.util.Scanner;
import jokeserver.User.Menus.AdminMenu;
import jokeserver.User.Menus.LoginMenu;
import jokeserver.User.Menus.RegistrationMenu;
import jokeserver.User.Model.User;
import jokeserver.User.Menus.UserMenu;

/**
 *
 * @author edoua
 */
public class JokeServerApplication {

     static void run() {
         boolean running=false;
            Scanner scanner = new Scanner(System.in);

        while (!running) {
            // Display login or registration options
            System.out.println("Welcome to Joke Server!");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    login();
                    break;
                case "2":
                    register();
                    break;
                case "3":
                    System.out.println("Exiting...");
                    running=true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void main(String[] args) {
        run();
    }
private static void login() {
        LoginMenu loginMenu = new LoginMenu();
        User currentUser = loginMenu.login();
        Scanner scan = new Scanner(System.in);
        if (currentUser != null) {
            boolean loggedIn = true;
            if (currentUser.getRole().equalsIgnoreCase("admin")) {
                AdminMenu adminMenu = new AdminMenu(currentUser);
                adminMenu.displayMenu();
                adminMenu.performAction(scan.nextLine());
            } else if (currentUser.getRole().equalsIgnoreCase("member")) {
                UserMenu userMenu = new UserMenu(currentUser);
                userMenu.displayMenu();
                
                userMenu.performAction(scan.nextLine());
            } else {
                System.out.println("Invalid user role");
            }
        } else {
            System.out.println("Failed to login. Please try again.");
        }
    }
 
    private static void register() {
        RegistrationMenu registrationMenu = new RegistrationMenu();
        boolean registered = registrationMenu.registerUser();

        if (registered) {
            System.out.println("User registration successful. Please login.");
        } else {
            System.out.println("Failed to register user. Please try again.");
        }
    }
}

