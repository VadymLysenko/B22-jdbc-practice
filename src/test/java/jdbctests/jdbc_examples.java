package jdbctests;

import org.junit.jupiter.api.Test;

import java.sql.*;

public class jdbc_examples {

    String dbUrl = "jdbc:oracle:thin:@54.92.248.102:1521:XE";
    String dbUsername = "hr";
    String dbPassword = "hr";


    @Test
    public void test1() throws SQLException {
        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * from departments");

        resultSet.next();
        System.out.println(resultSet.getString(2));

        while (resultSet.next()) {
            System.out.println(resultSet.getString(1) + " - " + resultSet.getString(2) +
                    " - " + resultSet.getString(3) + " - " + resultSet.getString(4));
//            resultSet.close();
//            statement.close();
//            connection.close();
            resultSet = statement.executeQuery("SELECT * from regions");
            while (resultSet.next()) {
                System.out.println(resultSet.getInt(1) + " - " + resultSet.getString(2));
            }

        }

    }

    @Test
    public void test2() throws SQLException {
        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("SELECT * from departments");

        //first way to get row
        resultSet.last();
        System.out.println(resultSet.getRow());
        //second way
        resultSet.last();
        int rowCount = resultSet.getRow();
        System.out.println(rowCount);
        //print all second column information
        resultSet.beforeFirst();
        while (resultSet.next()) {
            System.out.println(resultSet.getString(2));
        }

    }

    @Test
    public void test3() throws SQLException {
        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("SELECT * from employees");

        //get the database related data inside the DB Metadata object
        DatabaseMetaData dbMetadata = connection.getMetaData();
        System.out.println(dbMetadata.getUserName());
        System.out.println(dbMetadata.getDatabaseProductName());
        System.out.println(dbMetadata.getDatabaseMajorVersion());
        System.out.println(dbMetadata.getDriverName());
        System.out.println(dbMetadata.getDriverVersion());

        //get teh result set metadata //rsmd
        ResultSetMetaData rsMetaData = resultSet.getMetaData();

        int colCount = rsMetaData.getColumnCount();
        System.out.println(colCount);

        //getting column names
        System.out.println(rsMetaData.getColumnName(1));
        System.out.println(rsMetaData.getColumnName(2));

        for (int i = 1; i <= colCount; i++) {
            System.out.println(rsMetaData.getColumnName(i));

            }
        }
    }
