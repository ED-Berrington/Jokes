/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jokeserver.User.Services;

import java.util.ArrayList;
import jokeserver.User.DAO.MemberDAOImpl;
import jokeserver.User.Model.User;

/**
 *
 * @author edoua
 */
public class MemberServiceImpl implements MemberService {
    private MemberDAOImpl memberDAO;

    public MemberServiceImpl() {
        this.memberDAO = new MemberDAOImpl();
    }

    @Override
    public boolean createMember(String username, String password) {
        User member=new User(username,password);
        return memberDAO.createMember(member);
    }

    @Override
    public User getMemberById(int memberId) {
        return memberDAO.getMemberById(memberId);
    }

    @Override
    public ArrayList<User> getAllMembers() {
        return memberDAO.getAllMembers();
    }

    @Override
    public boolean updateMember(User member) {
        return memberDAO.updateMember(member);
    }

    @Override
    public boolean deleteMember(int memberId) {
        return memberDAO.deleteMember(memberId);
    }
}
