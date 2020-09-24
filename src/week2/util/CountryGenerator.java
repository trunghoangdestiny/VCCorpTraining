package week2.util;

import week2.dao.CityDAO;
import week2.model.Country;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CountryGenerator {
    public List<Country> generate(int start, int end) {
        List<Country> countryList = new ArrayList<>();
        CityDAO cityDAO = new CityDAO();

        for (int i = start; i < end; i++) {
            String code = "VN" + i;
            String name = "VietNam" + i;
            String continentCountryList[] = {"ASIA", "AMERICA", "AFRICA", "EUROPE"};
            String continent = continentCountryList[new Random().nextInt(continentCountryList.length)];
            int surfaceArea = new Random().nextInt(4000);
            int population = new Random().nextInt(5000);
            int capital = new Random().nextInt(cityDAO.getAllCity().size()) + 1;

            Country country = new Country(code, name, continent, surfaceArea, population, 0, capital);
            countryList.add(country);
        }
        return countryList;
    }
}
