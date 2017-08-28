package ru.geekbrains;

import java.sql.*;



/**
 * CREATE TABLE `Students` (
 `ID`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
 `Name`	TEXT,
 `Score`	INTEGER
 );
 */

public class Main {

    private static Connection connection;
    private static Statement stmt;

    public static void main(String[] args) throws SQLException {
        connect();
        stmt = connection.createStatement();
        stmt = connection.createStatement();
        //stmt.execute("INSERT INTO Students (Name, Score) VALUES ('Alex',50)"); //Insert data
        // int cnt  = stmt.executeUpdate("DELETE FROM Students WHERE Score < 20");//Delete data
        // System.out.println("DELETED ROWS: " + cnt);

        stmt.executeUpdate("UPDATE Students SET Score = 50 WHERE Name = 'Max'");

        ResultSet res = stmt.executeQuery("SELECT * FROM Students");

       // ResultSetMetaData rsmd = res.getMetaData();

        while(res.next()){
            System.out.println(res.getInt(1) + " " + res.getString(2) + " " +res.getInt(3));
        }

       // disconnect();

    }

    public static void connect(){
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:DataBase.db");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void disconnect(){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
