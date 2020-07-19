package com.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WeatherPage {

    @FindBy(id = "searchBox")
    private WebElement searchBox;
}
