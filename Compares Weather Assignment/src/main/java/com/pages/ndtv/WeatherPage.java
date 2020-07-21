package com.pages.ndtv;

import com.Logging;
import com.Wrapper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WeatherPage {

    private WebDriver driver;
    //***********************************************************************************
    //************************** Weather Page Locator ***********************************
    //***********************************************************************************
    @FindBy(id = "searchBox")
    public WebElement searchBox;


    //***********************************************************************************
    //**************************     Constructor  **************************************
    //***********************************************************************************
    public WeatherPage(WebDriver driver) {
        Logging.logInfo(this.getClass(), "Initialzing Constructor");

        PageFactory.initElements(driver, this);
        this.driver = driver;
    }


    //***********************************************************************************
    //********************************      Method **************************************
    //***********************************************************************************

    //searching city in search box
    public WeatherPage searchCity(String city) {
        Logging.logInfo(this.getClass(), "searching city " + city);
        Wrapper.sendkey(driver, searchBox, city);
        return this;
    }

    //validate checkbox is selected forcity if not then select
    public WeatherPage validateSearchedCityIsSelected(String city) {
        Logging.logInfo(this.getClass(), "validating checkbox is selected for city if not then select");
        WebElement element = driver.findElement(By.xpath("//div[@class='message']/label[@for='" + city + "']/input"));
        if (!element.isSelected()) {
            Logging.logDebug(this.getClass(), "City was not selected, Selecting city");
            Wrapper.click(driver, element);
        }
        return this;
    }

    //clicking selected city on map
    public WeatherPage clickCityOnMap(String city) {
        Logging.logInfo(this.getClass(), "clicking selected city" + city + " on map");
        Wrapper.click(driver, By.xpath("//div[@class='outerContainer' and @title='" + city + "']"));
        return this;
    }

    //Extract data from map like humoiddity, temp etc
    public WeatherData getCityWeatherDetail(String city) {
        Logging.logInfo(this.getClass(), "Extracting data of weather and storing in wether data class");
        return new WeatherData(driver.findElements(By.xpath("//span[contains(text(),'" + city + "')]/parent::div/parent::div/descendant::b")));
    }
}