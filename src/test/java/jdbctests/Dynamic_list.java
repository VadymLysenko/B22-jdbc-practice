package jdbctests;

import org.junit.jupiter.api.Test;

import javax.print.attribute.HashAttributeSet;
import javax.xml.stream.events.StartDocument;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dynamic_list {

    String dbUrl = "jdbc:oracle:thin:@54.92.248.102:1521:XE";
    String dbUsername = "hr";
    String dbPassword = "hr";

    @Test
    public void test2() throws SQLException {
        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select first_name,last_name,salary,job_id\n" +
                "from employees\n" +
                "where rownum < 6");

        ResultSetMetaData rsmd = resultSet.getMetaData();

        // list of maps
        List<Map<String,Object>> queryData = new ArrayList<>();

        //number of columns
        int colCount = rsmd.getColumnCount();

        //loop through each row
        while (resultSet.next()){

            Map<String,Object> row = new HashMap<>();

            //fill the map dynamically
            for (int i = 1; i <= colCount; i++) {
                row.put(rsmd.getColumnName(i),resultSet.getObject(i) );
            }

            //add ready map row to the list
            queryData.add(row);
        }
        //each row inside the list
        for (Map<String, Object> row : queryData) {
            System.out.println(row.toString());
        }
}
}