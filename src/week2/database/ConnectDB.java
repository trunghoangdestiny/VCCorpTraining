package week2.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

public class ConnectDB {
    private static String DB_URL = "jdbc:mysql://localhost:3306/mydatabase?useSSL=false"; //append "?useSSL=false" to the end of this string
    private static String USER_NAME = "root";
    private static String PASS = "";
    private static List<Connection> connectionPool = new ArrayList<>();
    private static final int MAX_CONNECTION = 1;
    private static int connection_index = 0;

    private synchronized static Connection getConnectionToMySql(String url, String userName, String passWord) { //this creates connectio;
        Connection connection = null;
        ++connection_index;
        try {
//            Class.forName("com.mysql.jdbc.Driver"); //not necessary
            if (connectionPool.size() < MAX_CONNECTION) {
                connection = DriverManager.getConnection(url, userName, passWord);
                connectionPool.add(connection);
                System.out.println("Connected successfully!" + connection.hashCode());
            } else {
                System.out.println("Connection has already been created.");
            }
        } catch (Exception e) {
            System.out.println("Connected fail!");
            e.printStackTrace();
        }
        return connectionPool.get((connection_index - 1) % MAX_CONNECTION); //return a connection to database if it's successfully
    }

    public static Connection getConnection() {
        return getConnectionToMySql(DB_URL, USER_NAME, PASS);
    }
}
