package Solution3;

import java.sql.*;

public class DataBase {
    private static Connection connection;
    private static Statement stmt;
    private static PreparedStatement ps;


    public String getDataBaseName() {
        return dataBaseName ;
    }

    private String dataBaseName;

    public DataBase(String dataBaseName) {
        this.dataBaseName = dataBaseName;
    }

}
