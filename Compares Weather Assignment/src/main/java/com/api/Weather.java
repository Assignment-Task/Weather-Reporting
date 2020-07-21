package com.api;

import com.ApiUtil;
import com.Logging;
import com.Wrapper;
import com.google.gson.Gson;
import com.pojo.weather.OpenWeatherMap;
import io.restassured.http.Method;
import io.restassured.response.Response;

import java.util.HashMap;

public class Weather {

    private ApiUtil apiUtil;
    private Response response;
    HashMap<String, String> queryParam = new HashMap<>();

    /**
     * calling open whether api for city
     * @param city
     * @return openweather map pojo class instance
     */
    public OpenWeatherMap getTemperatureInCelius(String city) {
   //     Logging.logInfo(this.getClass(),"Calling Temperature In Celius API");
        queryParam.clear();
        queryParam.put("q", city);
        queryParam.put("appid", Wrapper.propertyFile("APIkey"));
        queryParam.put("units", "metric");

        apiUtil = new ApiUtil();
        response = apiUtil.restcall(Wrapper.propertyFile("ApiBaseUrl") + "/data/2.5/weather",
                Method.GET, null, queryParam);
        return new Gson().fromJson(response.asString(), OpenWeatherMap.class);
    }

}
