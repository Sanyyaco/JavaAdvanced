package ru.geekbrains.HW;

import java.sql.*;

public class DataBase {
    private Connection connection;
    private Statement stmt;
    private static PreparedStatement ps;


    private String dataBaseName;

    public DataBase(String dataBaseName) {
        this.dataBaseName = dataBaseName;
    }

    public void connect(){
        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:"+dataBaseName;
            connection = DriverManager.getConnection(url);
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

    public void initTable(){
        try {
            stmt = connection.createStatement();
            stmt.execute("CREATE TABLE IF NOT EXISTS 'Commodity'( \n" +
                    "'ID' INTEGER PRIMARY KEY AUTOINCREMENT, \n" +
                    "'ProdID'  INTEGER UNIQUE, \n" +
                    "'Title'  TEXT, \n" +
                    "'Cost' DOUBLE);");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void populateTable() {
        try {
            stmt = connection.createStatement();
            connection.setAutoCommit(false);
            stmt.execute("DELETE FROM Commodity");
            stmt.execute("UPDATE SQLITE_SEQUENCE SET SEQ=0 WHERE NAME ='Commodity'");
            ps = connection.prepareStatement("INSERT INTO Commodity (ProdID, Title, Cost) VALUES (?, ?, ?)");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for (int i = 1; i <= 10000; i++) {
            try {
                ps.setInt(1,i);
                ps.setString(2,"товар"+i);
                ps.setDouble(3,Math.round(Math.random()*10000/3));
                ps.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        try {
            connection.commit();
            ps.close(); // Правильно, что я закрыл?
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getCommodityPrice(String commodityName){
        commodityName = commodityName.toLowerCase();
        String selectSQL = "SELECT Cost FROM Commodity WHERE Title = ?";
        ResultSet rs = null;
        int commodityPrice = 0;

        try {
            ps = connection.prepareStatement(selectSQL);
            ps.setString(1,commodityName);
            rs = ps.executeQuery();
            commodityPrice = rs.getInt("Cost");

        } catch (SQLException e) {
            e.printStackTrace();
        }


        if (commodityPrice != 0) {
            System.out.println(commodityPrice);

        }else{
            System.out.println("Такого товара нет.");
        }

        try {
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void changeCommodityPrice(String commodityName, double newPrice) {
        commodityName = commodityName.toLowerCase();
        String updateSQL = "UPDATE Commodity SET Cost = ? WHERE Title = ?";
        try {

            ps = connection.prepareStatement(updateSQL);
            ps.setDouble(1,newPrice);
            ps.setString(2,commodityName);
            ps.execute();

            System.out.print("Цена товара " + commodityName + " обновлена: ");
            getCommodityPrice(commodityName);
            System.out.println();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            connection.commit();
            ps.close(); // Правильно, что я закрыл?

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void getCommoditiesByPriceRange(double startPrice, double endPrice){
        double temp;
        PreparedStatement psq;

        if(startPrice > endPrice){
            temp = endPrice;
            endPrice = startPrice;
            startPrice = temp;
        }

        String selectSQL = "SELECT Title FROM Commodity WHERE Cost >= ? AND Cost <= ?";
        String selectQuantitySQL = "SELECT COUNT (*) FROM (" + selectSQL + ")";
        ResultSet rs = null;
        ResultSet rsq = null;

        try {
            ps = connection.prepareStatement(selectSQL);
            psq = connection.prepareStatement(selectQuantitySQL);
            ps.setDouble(1,startPrice);
            ps.setDouble(2,endPrice);
            rs = ps.executeQuery();
            psq.setDouble(1,startPrice);
            psq.setDouble(2,endPrice);
            rsq = psq.executeQuery();

            System.out.println("Результат поиска товаров по указанному ценовому диапазону: " + rsq.getInt(1) );
            while(rs.next()){
                System.out.println(rs.getString("Title"));;
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
