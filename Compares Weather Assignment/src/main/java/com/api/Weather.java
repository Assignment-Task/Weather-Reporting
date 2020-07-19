package com.api;

import com.ApiUtil;
import io.restassured.http.Method;

public class Weather {
//Celsius
//http://api.openweathermap.org/data/2.5/weather?q=Delhi&appid=7fe67bf08c80ded756e598d6f8fedaea&units=metric
//
//Imperial
//http://api.openweathermap.org/data/2.5/weather?q=Delhi&appid=7fe67bf08c80ded756e598d6f8fedaea&units=Imperial

    ApiUtil apiUtil;
    public void aa(){
        apiUtil.restcall("", Method.GET,null,null);
    }

}
