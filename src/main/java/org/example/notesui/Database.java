package org.example.notesui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    private static final String URL = "jdbc:sqlite:notes_app.db";

    public static Connection connect(){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL);
            System.out.println("Connect to SQLite has been established");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

    public static void createTables(){
        String usersTable = "create table users(" +
                "id integer primary key autoincrement," +
                "username text not null unique," +
                "password text not null" +
                ");";

        String notesTable = "create table notes(" +
                "id integer primary key autoincrement," +
                "user_id integer not null," +
                "title text not null," +
                "content text," +
                "foreign key (user_id) references users(id)" +
                ");";

        try {
            Connection connection = connect();
            Statement stmt = null;
            stmt = connection.createStatement();
            stmt.execute(usersTable);
            stmt.execute(notesTable);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
