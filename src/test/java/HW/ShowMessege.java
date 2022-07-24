package HW;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class ShowMessege {
    /*
    Test Case 1
1. Go to https://demo.seleniumeasy.com/basic-first-form-demo.html
2. In Single Input Field part enter any text
3. Click on Show Message button
4. Verify if the message matches the entered text
     */
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();

        options.addArguments("disable-popup-blocking");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://demo.seleniumeasy.com/basic-first-form-demo.html");

        WebElement message=driver.findElement(By.xpath("//div[@id=\"at-cv-lightbox-button-holder\"]/a[@href=\"#\"]"));
        if(message.isDisplayed()) {
            message.click();
        }
        }

            @Test
            public void CaseOne() {

                WebElement fieldText = driver.findElement(By.id("user-message"));
                fieldText.sendKeys("I like Selenium");
                WebElement messageBtn = driver.findElement(By.xpath("//button[@onclick=\"showInput();\"]"));
                messageBtn.click();
                String textBtn = driver.findElement(By.id("display")).getText();
                Assert.assertEquals(textBtn, "I like Selenium");
            }

            @Test
            public void CaseTwo() {
                String firstKey = "5";
                String secondKey = "7";
                WebElement firstNumber = driver.findElement(By.id("sum1"));
                firstNumber.sendKeys(firstKey);
                WebElement secondNumber = driver.findElement(By.id("sum2"));
                secondNumber.sendKeys(secondKey);
                WebElement getTotalBtn = driver.findElement(By.xpath("//button[@onclick=\"return total()\"]"));
                getTotalBtn.click();
                String actualResult = driver.findElement(By.xpath("//div/span[@id=\"displayvalue\"]")).getText();
                Integer num1 = Integer.valueOf(firstKey);
                Integer num2 = Integer.valueOf(secondKey);
                int sum = num1 + num2;
                String total = Integer.toString(sum);
                Assert.assertEquals(actualResult, total);

            }

            @Test
            public void CaseThree() {
                String firstKey = "a";
                String secondKey = "g";

                WebElement firstNumber = driver.findElement(By.id("sum1"));
                firstNumber.sendKeys(firstKey);


                WebElement secondNumber = driver.findElement(By.id("sum2"));
                secondNumber.sendKeys(secondKey);
                WebElement getTotalBtn = driver.findElement(By.xpath("//button[@onclick=\"return total()\"]"));
                getTotalBtn.click();
                String actualResult = driver.findElement(By.xpath("//div/span[@id=\"displayvalue\"]")).getText();


                Assert.assertEquals(actualResult, "NaN");

            }

            //@AfterMethod
            //public void tearDown() {
            //    driver.quit();
           // }
        }




