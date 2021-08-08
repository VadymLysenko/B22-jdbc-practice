package jdbctests;

import java.sql.*;

public class TestConnection {

    public static void main(String[] args) throws SQLException {

        String dbUrl = "jdbc:oracle:thin:@54.92.248.102:1521:XE";
        String dbUsername = "hr";
        String dbPassword = "hr";

        Connection connection = DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * from regions");

        //next() --> move pointer to first row
        resultSet.next();

        // getting information with column name
        System.out.println(resultSet.getString("region_name"));
        System.out.println(resultSet.getString(2));

        // close connections
        resultSet.close();
        statement.close();
        connection.close();

        //1 - Europe
        //2 - Americas
        System.out.println(resultSet.getInt(1)+ " - " + resultSet.getString(4));
        // move to second row
        resultSet.next();
        System.out.println(resultSet.getInt(1)+ " - " + resultSet.getString(2));
        resultSet.next();
        System.out.println(resultSet.getInt(1)+ " - " + resultSet.getString(2));

//        while(resultSet.next()){
//            System.out.println(resultSet.getInt(1)+ " - " + resultSet.getString(2));
//        }






    }
}
