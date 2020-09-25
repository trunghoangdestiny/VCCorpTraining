package week2.dao;

import week2.database.ConnectDB;
import week2.model.City;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CityDAO {
    Connection connection;

    public CityDAO() {
        connection = ConnectDB.getConnection();
    }

    public List<City> getAllCity() {
        List<City> cityList = new ArrayList<>();
        try {
            String sql = "select * from city";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                cityList.add(new City(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getInt(3), resultSet.getString(4)));
            }
        } catch (SQLException e) {
        }
        return cityList;
    }

    public List<City> getCitiesByCodeCountry(String codeCountry) {
        List<City> cityList = new ArrayList<>();
        try {
            String sql = "select * from city where city.codeCountry = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, codeCountry);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                City city = new City(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getInt(3), resultSet.getString(4));
                cityList.add(city);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cityList;
    }

    public List<City> getCitiesByContinent(String continent) {
        List<City> cityList = new ArrayList<>();
        try {
            String sql = "select city.id, city.name, city.population, city.codeCountry " +
                    "from country, city where city.codeCountry = country.code and country.continent = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, continent);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                City city = new City(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getInt(3), resultSet.getString(4));
                cityList.add(city);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cityList;
    }

    public City getCityById(int id) {
        City city = null;
        try {
            String sql = "select * from city where city.id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                city = new City(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getInt(3), resultSet.getString(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return city;
    }

    public void insert(City city) throws SQLException {
        String sql = "INSERT INTO `mydatabase`.`city` (`id`, `name`, `population`, `codeCountry`) VALUES (?, ?, ?, ?);";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            connection.setAutoCommit(false);

            preparedStatement.setInt(1, city.getId());
            preparedStatement.setString(2, city.getName());
            preparedStatement.setInt(3, city.getPopulation());
            preparedStatement.setString(4, city.getCodeCountry());
            preparedStatement.executeUpdate();

            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
        }
    }

    public void insertSlow(List<City> cityList) {
        cityList.forEach(city -> {
            try {
                insert(city);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    public void insertQuick(List<City> cityList) {
        try {
            if (cityList.size() == 0)
                return;
            StringBuilder sqlInsert = new StringBuilder("INSERT INTO `mydatabase`.`city` (`id`, `name`, `population`, `codeCountry`) VALUES ");
            StringBuilder sqlData = new StringBuilder("(?, ?, ?, ?),");
            for (int i = 0; i < cityList.size() - 1; i++) {
                sqlInsert.append(sqlData);
            }
            sqlInsert.append("(?, ?, ?, ?);");
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInsert.toString());
            for (int i = 0; i < cityList.size(); i++) {
                City city = cityList.get(i);
                preparedStatement.setInt(i * 4 + 1, city.getId());
                preparedStatement.setString(i * 4 + 2, city.getName());
                preparedStatement.setInt(i * 4 + 3, city.getPopulation());
                preparedStatement.setString(i * 4 + 4, city.getCodeCountry());
            }

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
