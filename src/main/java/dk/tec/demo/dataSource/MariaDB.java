package dk.tec.demo.dataSource;

import dk.tec.demo.Models.Person;

import java.sql.*;
import java.util.ArrayList;


public class MariaDB {
    private String conStr = "jdbc:mysql://localhost/persondb";
    private String user = "persondbuser";
    private String password = "kage1234";
    private Connection con;

    public MariaDB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
    private void connect(){
        try {
            con = DriverManager.getConnection(conStr, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(con != null){
            System.out.println("Connected");
        }
    }
    private void close() {
        if(con != null) {
            try {
                con.close();
                System.out.println("Closed");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    public ArrayList<Person> getAllPersons() {
        connect();
        ArrayList<Person> pList = new ArrayList<>();

        String sql = "select * from person";
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
  //           if(rs.next()) {
                while(rs.next()) {
                    Person p = new Person();
                    p.setPersId(rs.getInt("id"));
                    p.setFullName(rs.getString("fullName"));
                    p.setEmail(rs.getString("email"));
                    p.setNote(rs.getString("note"));
                    pList.add(p);
                }
//            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        close();
        return pList;

    }
    public Person getPerson(int persId) {
        Person p = new Person();
        connect();

        String sql = "select * from person where id = ?";
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, persId);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()) {
                p.setPersId(rs.getInt("id"));
                p.setFullName(rs.getString("fullName"));
                p.setEmail(rs.getString("email"));
                p.setNote(rs.getString("note"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        close();
        return p;
    }
    public int addPerson(Person pers) {
        connect();
        String sql = "insert into person (fullName, email, note) values (?, ?, ?)";
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, pers.getFullName());
            pstmt.setString(2, pers.getEmail());
            pstmt.setString(3, pers.getNote());
            pstmt.execute();

            close();
            return 1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());

            close();
            return 0;
        }
    }
    public int updatePersList(int persId, Person pers) {
        connect();
        int result = 0;

        String sql = "update person set fullName = ?, email = ?, note = ? where id = ?";
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, pers.getFullName());
            pstmt.setString(2, pers.getEmail());
            pstmt.setString(3, pers.getNote());
            pstmt.setInt(4, persId);
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        close();
        return result;
    }
    public int deletePerson(int persId) {
        connect();
        String sql = "delete from person where id = ?";

        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, persId);
            pstmt.execute();
            close();
            return 1;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            close();
            return 0;
        }
    }
}
