package jdbctests;

import org.junit.jupiter.api.Test;

import java.sql.*;

public class LibraryConnectionTest {

    @Test
    public void test1() throws SQLException {

        String dbUrl = "jdbc:mysql://3.89.251.99:3306/library1";
        String dbUsername = "library1_client";
        String dbPassword = "WVF4NdGXCKHeE6VQ";

        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * from books");

        resultSet.next();

        System.out.println(resultSet.getString(2));
    }
}