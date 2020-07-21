package com.pages.ndtv;

import org.openqa.selenium.WebElement;

import java.util.List;

public class WeatherData {

    String condition;
    String wind;
    String humidity;
    String tempInDegrees;
    String tempInFahrenheit;


    public WeatherData(List<WebElement> list) {
        for (int i = 0; i < list.size(); i++) {

            String value = list.get(i).getText();

            if (value.contains("Condition")) {
                setCondition(value.replace("Condition : ", ""));
            } else if (value.contains("Wind")) {
                setWind(value.replace("Wind: ", ""));
            } else if (value.contains("Humidity")) {
                setHumidity(value.replace("Humidity: ", "").replace("%", ""));
            } else if (value.contains("Temp in Degrees")) {
                setTempInDegrees(value.replace("Temp in Degrees: ", ""));
            } else if (value.contains("Temp in Fahrenheit")) {
                setTempInFahrenheit(value.replace("Temp in Fahrenheit: ", ""));
            }
        }
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getWind() {
        return wind;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getTempInDegrees() {
        return tempInDegrees;
    }

    public void setTempInDegrees(String tempInDegrees) {
        this.tempInDegrees = tempInDegrees;
    }

    public String getTempInFahrenheit() {
        return tempInFahrenheit;
    }

    public void setTempInFahrenheit(String tempInFahrenheit) {
        this.tempInFahrenheit = tempInFahrenheit;
    }
}