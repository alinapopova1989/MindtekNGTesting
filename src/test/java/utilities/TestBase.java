package utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestBase {
    protected WebDriver driver;

    @BeforeMethod(groups = {"regression", "smoke"})
    public void setUp() {
        driver = Driver.getDriver();
    }

//    @AfterMethod(groups = {"regression", "smoke"})

 //   public void tearDown() {
 //       driver.quit();
 //   }
}

