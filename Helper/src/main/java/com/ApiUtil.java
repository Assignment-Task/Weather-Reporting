package com;

import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ApiUtil {

    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static Response response = null;


    // ########################################################################################################
    // ######################################## CALLING API ###################################################
    // ########################################################################################################
    public Response restcall(String endpoints, Method method, List<Header> headerlist, Object param) {

        if (headerlist == null) {
            headerlist = new ArrayList<>();
            headerlist.add(new Header("Content-Type", "application/json"));
        }

        Headers headers = new Headers(headerlist);

        response = null;

        switch (method) {
            case GET:
                response = given()
                        .headers(headers)
                        .params((Map<String, ?>) param)
                        .get(endpoints);
                break;
            case POST:
                response = given()
                        .contentType(ContentType.JSON)
                        .headers(headers)
                        .body(param).post(endpoints);
                break;
            case PUT:
                response = given()
                        .contentType(ContentType.JSON)
                        .headers(headers)
                        .body(param).when().put(endpoints);
                break;
            case PATCH:
                response = given()
                        .contentType(ContentType.JSON)
                        .headers(headers)
                        .patch(endpoints);
                break;

            default:
                Logging.logInfo(this.getClass(), "Invalid Method Requested" + method);
                return null;
        }
        if (Logger.getRootLogger().getLevel().toString().equalsIgnoreCase("debug")) {
            //will write at console in debug mode
            printToConsole(endpoints, headers, method, param);
        }
        return response;
    }

    /**
     * This section will write logs on console if debug mode is on
     *
     * @param endpoints
     * @param headers
     * @param method
     * @param param
     */
    public void printToConsole(String endpoints, Headers headers, Method method, Object param) {

        Logging.logDebug(this.getClass(), ANSI_GREEN + "Calling : " + ANSI_RESET + endpoints);
        Logging.logDebug(this.getClass(), ANSI_GREEN + "Method : " + ANSI_RESET + method);
        Logging.logDebug(this.getClass(), ANSI_GREEN + "Header : " + ANSI_RESET);
        Logging.logDebug(this.getClass(), headers.toString());
        Logging.logDebug(this.getClass(), ANSI_GREEN + "Request body : " + ANSI_RESET);

        if (method.equals(Method.POST) || method.equals(Method.PUT)) {
            String str[] = param.toString().split(",");
            List<String> al = new ArrayList<String>();
            al = Arrays.asList(str);
            System.out.println();
            for (String s : al) {
                System.out.println(s);
            }
        } else {
            Logging.logDebug(this.getClass(),"No Body");
        }
        Logging.logDebug(this.getClass(), ANSI_GREEN + "Response Code: " + ANSI_RESET + response.statusCode());
        Logging.logDebug(this.getClass(), ANSI_GREEN + "Response Body: " + ANSI_RESET);
        response.prettyPrint();
    }

}