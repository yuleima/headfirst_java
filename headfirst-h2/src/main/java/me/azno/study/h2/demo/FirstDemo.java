package me.azno.study.h2.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class FirstDemo {
    public static void main(String[] args) throws Exception {
        Class.forName("org.h2.Driver");
        Connection conn = DriverManager.
                getConnection("jdbc:h2:D:/temp/20180611/test", "sa", "");
        // add application code here
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM TEST ");
        while (rs.next()) {
            System.out.println(rs.getInt("ID") + "," + rs.getString("NAME"));
        }
        conn.close();
    }
}
