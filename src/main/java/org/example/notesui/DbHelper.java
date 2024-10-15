package org.example.notesui;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DbHelper {

    // register
    // login
    // add note
    // get a note/notes
    // update a note
    // delete a note


    public static boolean registerUser(String username, String password){
        String sql = "insert into users(username, password) values(? ,?)";
        try{
            Connection connection = Database.connect();
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1,username);
            pstmt.setString(2,password); // hash the password
            pstmt.executeUpdate();
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static boolean loginUser(String username, String password){
        String sql = "select id, username from users where username= ? and password = ?";
        try{
            Connection connection = Database.connect();
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1,username);
            pstmt.setString(2,password); // compare hashed passwords

            ResultSet rs = pstmt.executeQuery();
            // if results found (there is a user)
            if(rs.next()){
                return true;
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    public static boolean addNote(int userId, String title, String content){
        String sql = "insert into notes(user_id, title, content) values(? ,?,?)";
        try{
            Connection connection = Database.connect();
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1,userId);
            pstmt.setString(2,title);
            pstmt.setString(3,content);
            pstmt.executeUpdate();
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static List<String> getNotes(int userId){
        List<String> notes = new ArrayList<>();

        String sql = "select title from notes where user_id=?";
        try{
            Connection connection = Database.connect();
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1,userId);

            ResultSet rs = pstmt.executeQuery();
            // if results found (there is a user)
            if(rs.next()){
                notes.add(rs.getString("title"));
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return notes;
    }

}
