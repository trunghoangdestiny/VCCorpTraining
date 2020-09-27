package week2.transaction_demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class TransactionDemo {
    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo_transaction?useSSL=false", "root", "");
        Statement statement = connection.createStatement();

        connection.setAutoCommit(false); //turn off auto commit mode

        try {
            statement.executeUpdate("UPDATE account_banking SET amount = 20000000.0 WHERE name = 'A'");
            connection.commit();
            statement.executeUpdate("UPDATE account_banking SET amount = 140000000.0 WHERE name = 'B'");
            connection.commit(); //data just update when this command is called
            System.out.println("Data updated successfully.");
        } catch (SQLException e) {
            System.out.println("Oops, there is something wrong.");
            e.printStackTrace();
            connection.rollback(); //if exception happens, roll back data
        }
        connection.close(); //close connection to database
    }
}
