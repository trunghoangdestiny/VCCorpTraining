package week2.query;

import week2.dao.*;
import week2.model.City;
import week2.model.Country;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Query {
    CountryDAO countryDAO = new CountryDAO();
    CityDAO cityDAO = new CityDAO();

    public void theMostCrowdedCityOfEachCountry() {
        List<City> cityList = new ArrayList<>();
        countryDAO.getAllCountry().forEach(country -> {
            City city = country.getCityList().stream().max(
                    (city1, city2) -> Integer.compare(city1.getPopulation(), city2.getPopulation())
            ).get();
            if (city != null)
                cityList.add(city);
        });
        System.out.println(cityList);
    }

    public void theMostCrowdedCityOfEachContinent() { //not optimized
        countryDAO.getAllCitiesOfContinent().forEach((continentKey, listCity) -> {
            City city = listCity
                    .stream()
                    .max((city1, city2) -> Integer.compare(city1.getPopulation(), city2.getPopulation()))
                    .get();
            System.out.printf("Continent: %s, City: %s \n", continentKey, city.getName());
        });
    }

    public void theMostCrowdedCityOfEachContinent2() {
        Map<String, List<City>> continentToCityList = new HashMap<>();

        countryDAO.getAllCountry().forEach((country -> {
            String continent = country.getContinent();
            if (!continentToCityList.containsKey(continent)) {
                continentToCityList.put(continent, new ArrayList<>());
            }
            country.getCityList().forEach(city -> {
                continentToCityList.get(continent).add(city);
            });
        }));

        continentToCityList.forEach((continentKey, listCity) -> {
            City city = listCity
                    .stream()
                    .max((city1, city2) -> Integer.compare(city1.getPopulation(), city2.getPopulation()))
                    .get();
            System.out.printf("Continent: %s, City: %s \n", continentKey, city.getName());
        });
    }

    public void theMostCrowdedCapital() {
        List<City> capitalList = new ArrayList<>();
        countryDAO.getAllCountry().forEach(country -> {
            int capital = country.getCapital();
            capitalList.add(cityDAO.getCityById(capital));
        });

        System.out.println(capitalList
                .stream()
                .max((city1, city2) -> Integer.compare(city1.getPopulation(), city2.getPopulation()))
                .get());
    }

    public void theMostCrowdedCityIsCapitalOfContinent() {
        Map<String, List<City>> continentToCapitalList = new HashMap<>();

        countryDAO.getAllCountry().forEach((country -> {
            String continent = country.getContinent();
            int capital = country.getCapital();
            if (!continentToCapitalList.containsKey(continent)) {
                continentToCapitalList.put(continent, new ArrayList<>());
            }
            continentToCapitalList.get(continent).add(cityDAO.getCityById(capital));
        }));

        continentToCapitalList.forEach((continentKey, listCapital) -> {
            City city = listCapital
                    .stream()
                    .max((city1, city2) -> Integer.compare(city1.getPopulation(), city2.getPopulation()))
                    .get();
            System.out.printf("Continent: %s, City: %s \n", continentKey, city.getName());
        });
    }

    public void arrangeCountryByCityNumber() {
        List<Country> countryList = countryDAO.getAllCountry();
        countryList.sort((country1, country2) -> {
            return country2.getCityList().size() - country1.getCityList().size();
        });
        countryList.forEach(country -> System.out.println(country.getName() + " " + country.getCityList().size()));
    }

    public void arrangeCountryByPopulationDensity() {
        List<Country> countryList = countryDAO.getAllCountry();
        countryList.sort((country1, country2) -> {
            double density1 = country1.getPopulation() / country1.getSurfaceArea();
            double density2 = country2.getPopulation() / country2.getSurfaceArea();
            return (int) (density2 - density1);
        });
        countryList.forEach(country -> {
            int density = (int)(country.getPopulation() / country.getSurfaceArea());
            System.out.printf("%s: %d people/km2\n",country.getName(), density);
        });
    }

    public static void main(String[] args) {
        new Query().theMostCrowdedCityOfEachCountry();
//        new Query().theMostCrowdedCityOfEachContinent2();
//        new Query().theMostCrowdedCapital();
//        new Query().theMostCrowdedCityIsCapitalOfContinent();
    }
}
