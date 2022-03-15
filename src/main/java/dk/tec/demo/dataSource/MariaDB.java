package dk.tec.demo.dataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MariaDB {
    private String conStr = "jdbc:mysql://local/persondb";
    private String user = "persondbuser";
    private String password = "kage1234";
    private Connection con;

    public MariaDB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            con = DriverManager.getConnection(conStr, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
