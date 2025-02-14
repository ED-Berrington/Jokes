/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jokeserver.User.DAO;

import jokeserver.User.Model.User;
import jokeserver.DBConfig.DBConfig;
import java.sql.*;
import java.util.ArrayList;



/**
 *
 * @author edoua
 */
public class MemberDAOImpl extends DBConfig implements memberDAO  {
 private DBConfig dbconfig;

    public MemberDAOImpl() {
        this.dbconfig = new DBConfig();
    }
 
    @Override
    public boolean createMember(User member) {
        String add="INSERT INTO users(username,password) VALUES(?,?)";
        PreparedStatement ps=null;
        try(Connection con=dbconfig.getConnector();){
        ps=con.prepareStatement(add);
        ps.setString(1, member.getUsername());
        ps.setString(2, member.getPassword());
        int rows=ps.executeUpdate();
        return rows>0;
        }
        catch (SQLException ex) {
         return false;
     }finally{
        if(ps!=null){
            try {
                ps.close();
            } catch (SQLException ex) {
                System.out.println("failed to create member"+ex.getMessage());
            }
        }
        }
    }
    
    @Override
    public User getMemberById(int memberId) {
            String query = "SELECT * FROM users WHERE UserID = ?";
    try (Connection con = dbconfig.getConnector();
         PreparedStatement ps = con.prepareStatement(query)) {
        ps.setInt(1, memberId);
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                int id = rs.getInt("UserID");
                String username = rs.getString("username");
                String role = rs.getString("role");
                return new User(id, username, role);
            }
        }
    } catch (SQLException e) {
        System.out.println("failed to get memeber");
    }
    return null ;
    }
    
    @Override
    public ArrayList<User> getAllMembers() {
        ArrayList<User> members=new ArrayList<>();
        String fetch="SELECT * FROM users";
        PreparedStatement ps=null;
        ResultSet rs=null;
        try(Connection con=dbconfig.getConnector()){
         ps=con.prepareStatement(fetch);
        rs=ps.executeQuery();
        while(rs.next()){
        int id=rs.getInt("UserID");
        String username=rs.getString("username");
        String role=rs.getString("role");
        User user=new User(id,username,role);
        members.add(user);
        }
        }catch(SQLException e){
        
        }finally{
                if(ps!=null){
            try {
                ps.close();
            } catch (SQLException ex) {
                System.out.println("Failed to get users");
            }
        }
        }
return members;    }

    @Override
    public boolean updateMember(User member) {
        String query = "UPDATE users SET username = ?, role = ? WHERE UserID = ?";
    try (Connection con = dbconfig.getConnector();
         PreparedStatement ps = con.prepareStatement(query)) {
        ps.setString(1, member.getUsername());
        ps.setString(2, member.getRole());
        ps.setInt(3, member.getId());
        int rowsAffected = ps.executeUpdate();
        return rowsAffected > 0;
    } catch (SQLException e) {
        System.out.println("Failed to update member");
        return false;
    }
    }

    @Override
    public boolean deleteMember(int memberId) {
            String query = "DELETE FROM users WHERE UserID = ?";
    try (Connection con = dbconfig.getConnector();
         PreparedStatement ps = con.prepareStatement(query)) {
        ps.setInt(1, memberId);
        int rowsAffected = ps.executeUpdate();
        return rowsAffected > 0;
    } catch (SQLException e) {
        System.out.println("Failed to delete user");
        return false;
    }
    }
    
}
