package com;

import com.api.Weather;
import com.pages.ndtv.HomePage;
import com.pages.ndtv.WeatherData;
import com.pages.ndtv.WeatherPage;
import com.pojo.weather.OpenWeatherMap;
import com.util.BaseTest;
import com.util.WeatherComparator;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TC_001 extends BaseTest {

    private String city;

    private HomePage homePage;
    private WeatherPage weatherPage;
    private WeatherData weatherData;
    private OpenWeatherMap openWeatherMap;
    private Weather weather;
    private WeatherComparator compare;

    @Test
    @Parameters({"city"})
    public void getWeatherFromNdtv(String city) {
        //Setting city variable of class
        this.city = city;

        //Opening browser instance
        driver = DriverFactory.getDriver(DriverType.CHROME);

        //Creating class instance with webdriver instance
        homePage = new HomePage(driver);

        homePage
                .navigateToNdtv()   // Navigating to NDTV url
                .selectSubmenuOnHomePage()   // click ... for submenu option
                .selectWeatherMenuOnHomePage();  // click weather from submenu

        //Creating class instance with webdriver instance
        weatherPage = new WeatherPage(driver);

        weatherData = weatherPage
                .searchCity(city)     //Searching the city on map by typing in search box
                .validateSearchedCityIsSelected(city)     // Checking if search city visible on map if not click checkbox
                .clickCityOnMap(city)                   // clicking the searched city on map
                .getCityWeatherDetail(city);            // Extracting weather data related to city and storing in weatherdata getter setter
    }

    @Test(dependsOnMethods = {"getWeatherFromNdtv"})
    public void getWeatherFromOpenWeatherApi() {
        weather = new Weather();
        openWeatherMap = weather.getTemperatureInCelius(city); // Calling weather api and storing data in pojo class
    }

    @Test(dependsOnMethods = {"getWeatherFromOpenWeatherApi"})
    @Parameters({"temp", "humidity"})
    public void varianceLogic(double temp, double humidity) {

        Logging.logInfo(this.getClass(),"Variance Logic");

        //Creating Weather Comparator class instance
        compare = new WeatherComparator();

        // Checking variance of humidity and getting response as true or false
        boolean varHumidity = compare.varianceHumidity(openWeatherMap, weatherData, humidity);
        // Checking variance of temperature and getting response as true or false
        boolean varTemp = compare.varianceTemperature(openWeatherMap, weatherData, temp);

        // Comparing the variance of humidity and temperature, based on that updating test case status
        if (varHumidity && varTemp) {
            Logging.logInfo(this.getClass(), "Weather has been successfully validated from two sources");
        } else {
            Assert.fail("Varaince difference is more than expected");
        }

    }
}