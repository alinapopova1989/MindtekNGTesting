package HW;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class DropdownList {
/*
Test Case 1
1. Go to https://demo.seleniumeasy.com/basic-select-dropdown-demo.html
2. In Select List Demo part select Wednesday from dropdown by using index
3. Validate it using TestNG

 */

    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }
    @Test
    public void Dropdown(){
        driver.get("https://demo.seleniumeasy.com/basic-select-dropdown-demo.html");
        WebElement dropDown=driver.findElement(By.id("select-demo"));
        Select dropSelect=new Select(dropDown);
        dropSelect.selectByIndex(4);
        String expectedResult="Wednesday";
        String actualResult=driver.findElement(By.xpath("//option[@value=\"Wednesday\"]")).getText();
        Assert.assertEquals(expectedResult,actualResult,"pass");

    }
    @Test
    public void DropdownByValue(){
        driver.get("https://demo.seleniumeasy.com/basic-select-dropdown-demo.html");
        WebElement dropDown=driver.findElement(By.id("select-demo"));
        Select dropSelect=new Select(dropDown);
        dropSelect.selectByValue("Saturday");
        String expectedResult="Saturday";
        String actualResult=driver.findElement(By.xpath("//option[@value=\"Saturday\"]")).getText();
        Assert.assertEquals(expectedResult,actualResult,"pass");
    }
    //@AfterMethod
    //public void End(){
    //    driver.quit();
   // }
}