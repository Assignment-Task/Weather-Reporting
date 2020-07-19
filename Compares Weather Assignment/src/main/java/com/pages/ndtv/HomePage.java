package com.pages.ndtv;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    //***********************************************************************************
    //************************** Home Page Locator **************************************
    //***********************************************************************************
    @FindBy(id = "h_sub_menu")
    private WebElement subMenu;

    @FindBy(xpath = "//div[@class='topnav_cont']/descendant::a[text()='WEATHER']")
    private WebElement weatherMenu;

    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void selectSubmenuOnHomePage() {
        subMenu.click();
    }

    public void selectWeatherMenuOnHomePage() {
        weatherMenu.click();
    }
}