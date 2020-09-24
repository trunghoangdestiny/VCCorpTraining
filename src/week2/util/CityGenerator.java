package week2.util;

import week2.model.City;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CityGenerator {

    public List<City> generate(int startID, int endID) {
        List<City> cityList = new ArrayList<>();

        for (int i = startID; i < endID; i++) {
            String name = "Vinh Yen " + i;
            int population = new Random().nextInt(1000) + 1;
            String codeCountry = "VN" + (i % 9 + 1);
            City city = new City(i, name, population, codeCountry);
            cityList.add(city);
        }

        return cityList;
    }
}
