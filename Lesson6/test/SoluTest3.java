package Solution3;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.*;

public class SoluTest3 {
    public DataBase db;
    private Connection connection;
    private static Statement stmt;
    private static PreparedStatement ps;

    @Before
    public void startTest(){
        db = new DataBase("Students.db");
        //connect db
        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:"+db.getDataBaseName();
            connection = DriverManager.getConnection(url);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //init table
        try {
            stmt = connection.createStatement();
            stmt.execute("CREATE TABLE IF NOT EXISTS 'Students'( \n" +
                    "'ID' INTEGER PRIMARY KEY AUTOINCREMENT, \n" +
                    "'Name'  TEXT, \n" +
                    "'Rate' INTEGER);");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //populate db
        try {
            stmt = connection.createStatement();
            connection.setAutoCommit(false);
            stmt.execute("DELETE FROM Students");
            stmt.execute("UPDATE SQLITE_SEQUENCE SET SEQ=0 WHERE NAME ='Students'");
            ps = connection.prepareStatement("INSERT INTO Students (Name, Rate) VALUES (?, ?)");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for (int i = 1; i <= 100; i++) {
            try {
                ps.setString(1,"Студент"+i);
                ps.setInt(2,6);
                ps.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        try {
            connection.setAutoCommit(true);
            ps.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void createEntry() throws SQLException {
        stmt = connection.createStatement();
        int i = stmt.executeUpdate("INSERT INTO Students (Name, Rate) VALUES ('Федя', 10)");
        if(i>0) Assert.assertTrue(true);
        else Assert.assertTrue(false);
        stmt.close();

    }

    @Test
    public void updateEntry() throws SQLException {
        createEntry();
        stmt = connection.createStatement();
        int i = stmt.executeUpdate("UPDATE Students SET Rate = 50 WHERE Name = 'Федя'");
        if(i>0) Assert.assertTrue(true);
        else Assert.assertTrue(false);

    }

    @Test
    public void selectEntry() throws SQLException {
        updateEntry();
        stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT Rate FROM Students WHERE Name = 'Федя'");
        int i = rs.getInt(1);
        if(i==50) Assert.assertTrue(true);
        else Assert.assertTrue(false);
    }

    @Test
    public void deleteEntry() throws SQLException {
        updateEntry();
        stmt = connection.createStatement();
        int i = stmt.executeUpdate("DELETE FROM Students WHERE Name = 'Федя'");
        if(i>0) Assert.assertTrue(true);
        else Assert.assertTrue(false);
    }
}
