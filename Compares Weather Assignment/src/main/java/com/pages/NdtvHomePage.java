package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NdtvHomePage {

    @FindBy(id = "h_sub_menu")
    private WebElement subMenu;

    @FindBy(xpath = "//div[@class='topnav_cont']/descendant::a[text()='WEATHER']")
    private WebElement weatherMenu;

    public NdtvHomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void selectSubmenuOnHomePage() {
        subMenu.click();
    }

    public void selectWeatherMenuOnHomePage() {
        weatherMenu.click();
    }
}