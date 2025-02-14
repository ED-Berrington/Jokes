/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package jokeserver.User.DAO;

import jokeserver.User.Model.User;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author edoua
 */
public interface memberDAO {
        boolean createMember(User member);

    User getMemberById(int memberId);

    ArrayList<User> getAllMembers();

    boolean updateMember(User member);

    boolean deleteMember(int memberId);
}
