package week2.dao;

import week2.database.ConnectDB;
import week2.model.City;
import week2.model.Country;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CountryDAO {
    Connection connection;
    private final CityDAO cityDAO = new CityDAO();

    public CountryDAO() {
        connection = ConnectDB.getConnection();
    }

    public List<Country> getAllCountry() {
        List<Country> countryList = new ArrayList<>();
        try {
            String sql = "select * from country";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Country country = buildCountry(resultSet);
                List<City> cityList = cityDAO.getCitiesByCodeCountry(country.getCode());
                country.setCityList(cityList);
                countryList.add(country);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return countryList;
    }

    public Map<String, List<City>> getAllCitiesOfContinent() {
        Map<String, List<City>> continentMap = new HashMap<>();
        try {
            String sql = "select distinct country.continent from country";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                List<City> cityList = cityDAO.getCitiesByContinent(resultSet.getString(1));
                continentMap.put(resultSet.getString(1), cityList);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return continentMap;
    }

    private static Country buildCountry(ResultSet resultSet) throws SQLException {
        Country country = new Country(resultSet.getString(1), resultSet.getString(2),
                resultSet.getString(3), resultSet.getDouble(4), resultSet.getInt(5),
                resultSet.getDouble(6), resultSet.getInt(7));
        return country;
    }

    public void insert(Country country) {
        String sql = "INSERT INTO `mydatabase`.`country` " +
                "(`code`, `name`, `continent`, `sufaceArea`, `population`, `gnp`, `capital`) " +
                "VALUES (?, ?, ?, ?, ?, null, ?);";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, country.getCode());
            preparedStatement.setString(2, country.getName());
            preparedStatement.setString(3, country.getContinent());
            preparedStatement.setDouble(4, country.getSurfaceArea());
            preparedStatement.setInt(5, country.getPopulation());
            preparedStatement.setInt(6, country.getCapital());

            preparedStatement.executeUpdate();
            System.out.println("Insert country successful.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insert(List<Country> countryList){
        countryList.forEach(country -> insert(country));
    }
}
