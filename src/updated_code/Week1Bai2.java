package updated_code;

import java.sql.*;

public class Week1Bai2 {
    private static String URL = "jdbc:mysql://localhost:3306/mydatabase?useSSL=false";
    private static String USER = "root";
    private static String PASS = "";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASS)) {
            System.out.println("Connected successfully.");
            String query = "select *\n" +
                    "from (\n" +
                    "         select country.code,\n" +
                    "                country.name as CountryName,\n" +
                    "                city.name       CityName,\n" +
                    "                city.population,\n" +
                    "                ROW_NUMBER() over (\n" +
                    "                    partition by country.code\n" +
                    "                    order by city.population desc\n" +
                    "                    )        as row_num\n" +
                    "         from city,\n" +
                    "              country\n" +
                    "         where codeCountry = country.code\n" +
                    "     ) rank_city_of_each_country\n" +
                    "where row_num = 1;";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1) + ", " + resultSet.getString(2) + ": " + resultSet.getString(3)
                        + ", " + resultSet.getInt(4));
            }
        } catch (SQLException e) {
            System.out.println("Connected failed.");
            e.printStackTrace();
        }
    }
}
