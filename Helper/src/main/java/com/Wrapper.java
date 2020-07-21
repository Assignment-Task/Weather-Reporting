package com;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Wrapper {
    private WebDriverWait wait;
    private static Integer TIMEOUT = 10;

    public static String propertyFile(String propertyname) {

        File file = new File(System.getProperty("user.dir") + "\\src\\test\\resources\\config.properties");

        FileInputStream fileInput = null;
        try {
            fileInput = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Properties prop = new Properties();

        try {
            prop.load(fileInput);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop.getProperty(propertyname);
    }


    public static void click(WebDriver driver, Object element) {
        Logging.logDebug(Wrapper.class, "Performing click action");
        if (element instanceof By)
            element = driver.findElement((By) element);

        WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
        wait.until(ExpectedConditions.visibilityOf((WebElement) element));
        wait.until(ExpectedConditions.elementToBeClickable((WebElement) element));
        ((WebElement) element).click();
    }

    public static void sendkey(WebDriver driver, Object element, Object text) {
        Logging.logDebug(Wrapper.class, "Performing sendkey action");
        if (element instanceof By)
            element = driver.findElement((By) element);

        WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
        wait.until(ExpectedConditions.visibilityOf((WebElement) element));
        ((WebElement) element).sendKeys(text.toString());
    }

    public static void navigateTo(WebDriver driver, String url) {
        Logging.logDebug(Wrapper.class, "Performing navigate to action");
        driver.get(url);
    }
}