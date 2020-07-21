package com;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

public class DriverFactory {

    private static final Map<DriverType, Supplier<WebDriver>> driverMap = new HashMap<>();

    //chrome driver supplier
    private static final Supplier<WebDriver> chromeDriverSupplier = () -> {
        Logging.logInfo(DriverFactory.class,"Initializing chrome browser");
        // Create object of HashMap Class
        Map<String, Object> prefs = new HashMap<String, Object>();

        // Set the notification setting it will override the default setting
        prefs.put("profile.default_content_setting_values.notifications", 2);

        // Create object of ChromeOption class
        ChromeOptions options = new ChromeOptions();

        // Set the experimental option
        options.setExperimentalOption("prefs", prefs);

        //Set chrome window maximized
        options.addArguments("--start-maximized");

        // pass the options object in Chrome driver
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\src\\test\\resources\\BrowserDriver\\chromedriver.exe");
        return new ChromeDriver(options);
    };

    //firefox driver supplier
    private static final Supplier<WebDriver> firefoxDriverSupplier = () -> {
        Logging.logInfo(DriverFactory.class,"Initializing firefox browser");
        System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "\\src\\test\\resources\\BrowserDriver\\geckodriver64.exe");
        return new FirefoxDriver();
    };


    //add all the drivers into a map
    static {
        driverMap.put(DriverType.CHROME, chromeDriverSupplier);
        driverMap.put(DriverType.FIREFOX, firefoxDriverSupplier);
    }

    //return a new driver from the map
    public static final WebDriver getDriver(DriverType type) {
        WebDriver driver = driverMap.get(type).get();

        //setting Implicitl Wait
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        return driver;
    }

}
