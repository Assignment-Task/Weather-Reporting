package com.util;

import com.Logging;
import com.pages.ndtv.WeatherData;
import com.pojo.weather.OpenWeatherMap;

public class WeatherComparator {

    /**
     * Compare temperature variance
     * * @param openWeatherMap
     *
     * @param weatherData
     * @param expectedVariance
     * @return true if temperature is in variance else false
     */
    public boolean varianceTemperature(OpenWeatherMap openWeatherMap, WeatherData weatherData, double expectedVariance) {
        Logging.logDebug(this.getClass(), "Value of temperature from openweather api " + openWeatherMap.getMain().getTemp());
        Logging.logDebug(this.getClass(), "Value of temperature from ndtv " + Double.valueOf(weatherData.getTempInDegrees()));


        double actualVariance = Math.abs(openWeatherMap.getMain().getTemp() - Double.valueOf(weatherData.getTempInDegrees()));
        Logging.logDebug(this.getClass(), "Variance difference is " + actualVariance);

        //returning true is temperature is in variance range
        return actualVariance <= expectedVariance ? true : false;
    }

    /**
     * Compare humidity variance
     *
     * @param openWeatherMap
     * @param weatherData
     * @param expectedVariance
     * @return true if humidity is in variance else false
     */
    public boolean varianceHumidity(OpenWeatherMap openWeatherMap, WeatherData weatherData, double expectedVariance) {
        Logging.logDebug(this.getClass(), "Value of Humidity from openweather api " + openWeatherMap.getMain().getHumidity());
        Logging.logDebug(this.getClass(), "Value of Humidity from ndtv " + Double.valueOf(weatherData.getHumidity()));

        double actualVariance = Math.abs(openWeatherMap.getMain().getHumidity() - Double.valueOf(weatherData.getHumidity()));
        Logging.logDebug(this.getClass(), "Variance difference is " + actualVariance);

        //returning true is hudimity is in variance range
        return actualVariance <= expectedVariance ? true : false;

    }
}
