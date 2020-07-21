package com.util;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;

public class BaseTest {
    protected WebDriver driver;

    @AfterTest
    public void teardown() {
        driver.close();
    }
}
