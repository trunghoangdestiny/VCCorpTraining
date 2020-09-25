package week2.query;

import week2.dao.CityDAO;
import week2.dao.CountryDAO;
import week2.util.CityGenerator;
import week2.util.CountryGenerator;

import java.sql.SQLException;

public class MainTest {
    public static void main(String[] args) throws SQLException {
//        ExecutorService executorService = Executors.newSingleThreadExecutor();
//        final int numberRecords = 1500000; //number of records * 5, cause there are 5 threads
//        for (int i = 0; i < 1; i++) {
//            final int _i = i;
//            executorService.execute(() -> {
//                TimeCounter test1 = new TimeCounter(() -> {
//                    List<City> cityList = new CityGenerator().generate(numberRecords * _i, _i * numberRecords + numberRecords);
//                    new CityDAO().insertQuick(cityList);
//                });
//                test1.count();
//            });
//        }
//        executorService.shutdown();

//        TimeCounter test = new TimeCounter(() -> { //very optimized with 300000 ms :))
//            List<City> cityList = new CityGenerator().generate(1, 500000);
//            String sqlInsert = "call insertCityList(?, ?, ?, ?);";
//            Connection connection = ConnectDB.getConnection();
//            for (int i = 0; i < 50000; i++) {
//                try {
//                    CallableStatement callableStatement = connection.prepareCall(sqlInsert);
//
//                    callableStatement.setInt(1, cityList.get(i).getId());
//                    callableStatement.setString(2, cityList.get(i).getName());
//                    callableStatement.setInt(3, cityList.get(i).getPopulation());
//                    callableStatement.setString(4, cityList.get(i).getCodeCountry());
//
//                    callableStatement.executeUpdate();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//        test.count();

        new CityDAO().insertQuick(new CityGenerator().generate(1, 1000));
        new CountryDAO().insert(new CountryGenerator().generate(1, 10));
    }
}
