import com.DriverFactory;
import com.DriverType;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class d {

    WebDriver driver;

    @BeforeTest
    public void setup(){
        driver = DriverFactory.getDriver(DriverType.CHROME);
    }

    @Test
    public void test1(){
        driver.get("https://www.google.com");
        //your test using webdriver
    }

    @AfterTest
    public void teardown(){
        driver.close();
    }
}
