package com.pages.ndtv;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WeatherPage {

    //***********************************************************************************
    //************************** Weather Page Locator ***********************************
    //***********************************************************************************
    @FindBy(id = "searchBox")
    public WebElement searchBox;

    public WeatherPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}
