/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package jokeserver.User.Services;

import java.util.ArrayList;
import jokeserver.User.Model.User;

/**
 *
 * @author edoua
 */
public interface MemberService {
        boolean createMember(String username, String password);
    User getMemberById(int memberId);
    ArrayList<User> getAllMembers();
    boolean updateMember(User member);
    boolean deleteMember(int memberId);
}
