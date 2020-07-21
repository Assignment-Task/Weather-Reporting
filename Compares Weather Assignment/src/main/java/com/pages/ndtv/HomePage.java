package com.pages.ndtv;

import com.Logging;
import com.Wrapper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class HomePage {

    //***********************************************************************************
    //************************** Home Page Locator **************************************
    //***********************************************************************************
    //SubMenu option
    @FindBy(id = "h_sub_menu")
    private WebElement subMenu;

    //Weather menu
    @FindBy(xpath = "//div[@class='topnav_cont']/descendant::a[text()='WEATHER']")
    private WebElement weatherMenu;

    //Pop Up Alert when ndtv psge loads up
    @FindBy(xpath = "//a[text()='No Thanks']")
    private WebElement popupAlert;

    private WebDriver driver;


    //***********************************************************************************
    //**************************     Constructor  **************************************
    //***********************************************************************************
    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }


    //***********************************************************************************
    //********************************      Method **************************************
    //***********************************************************************************

    //clicking main menu dot ...
    public HomePage selectSubmenuOnHomePage() {
        Logging.logInfo(this.getClass(), "clicking ... to enable submenu");
        Wrapper.click(driver, subMenu);
        return this;
    }

    //clicking weather menu from sub menu
    public HomePage selectWeatherMenuOnHomePage() {
        Logging.logInfo(this.getClass(), "clicking wether menu from sub menu");

        Wrapper.click(driver, weatherMenu);

        //comparing page title
        Assert.assertEquals(driver.getTitle().trim(), "NDTV Weather - Weather in your Indian City", "Mismatch Weather Map Page Title");

        return this;
    }

    //Navigating to ndtv and closing pop up
    public HomePage navigateToNdtv() {
        Logging.logInfo(this.getClass(), "Navigating to ndtv " + Wrapper.propertyFile("NdtvUrl"));

        //navigate to ndtv
        Wrapper.navigateTo(driver, Wrapper.propertyFile("NdtvUrl"));

        //comparing page title
        Assert.assertEquals(driver.getTitle().trim(), "NDTV: Latest News, India News, Breaking News, Business, Bollywood, Cricket, Videos & Photos", "Mismatch NDTV Page Title");

        //closing pop up alert
        Wrapper.click(driver, popupAlert);

        return this;
    }
}