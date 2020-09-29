package updated_code;

import java.sql.*;
import java.util.Scanner;

public class Week2 {
    private static String URL = "jbdc:mysql://localhost:3306/mydatabase?useSSL=false";
    private static String USER = "root";
    private static String PASS = "";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASS)) {
            String query1 = "";
            PreparedStatement preparedStatement = connection.prepareStatement(query1);
            ResultSet resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            System.out.println("Connect failed.");
            e.printStackTrace();
        }

    }
}
