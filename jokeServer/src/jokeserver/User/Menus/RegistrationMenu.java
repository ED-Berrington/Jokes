/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jokeserver.User.Menus;

/**
 *
 * @author edoua
 */
import java.util.Scanner;
import jokeserver.User.Services.MemberServiceImpl;

public class RegistrationMenu {
    private Scanner scanner = new Scanner(System.in);
    private MemberServiceImpl memberService;

    public RegistrationMenu() {
        this.memberService = new MemberServiceImpl();
    }


    public boolean registerUser() {
        System.out.println("=== User Registration ===");
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        return memberService.createMember(username, password);
    }
}
