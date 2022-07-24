package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class JSExecutor {
    WebDriver driver;
    String item = "web camera";

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();

    }

    @Test
    public void searchTest() {
        driver.get("https://www.amazon.com/");

        driver.findElement(By.id("twotabsearchtextbox")).sendKeys(item + Keys.ENTER);
        String searchItem = driver.findElement(By.xpath("//span[@class=\"a-color-state a-text-bold\"]")).getText();
        Assert.assertEquals(searchItem.substring(1,searchItem.length()-1),item);
    }
    @Test
    public void outOfStockCheckboxTest(){
        driver.get("https://www.amazon.com/");
        driver.findElement(By.id("twotabsearchtextbox")).sendKeys(item + Keys.ENTER);

        JavascriptExecutor jse=((JavascriptExecutor)driver);
        jse.executeScript("window.scrollBy(0,1500)");

         driver.findElement(By.xpath("//li[@id='p_n_availability/2661601011']//i")).click();
         jse.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }
    @Test(priority = 1)
    public void jseTest(){
        //driver.get("https://www.amazon.com/");
        JavascriptExecutor jse=((JavascriptExecutor)driver);
        jse.executeScript("window.location='https://www.amazon.com/'");
        WebElement customerServiceTab=driver.findElement(By.xpath("//*[@id=\"nav-xshop\"]/a[5]"));
        jse.executeScript("arguments[0].click()",customerServiceTab);
        Assert.assertEquals(driver.getTitle(),"Amazon Customer Service Support – Amazon.com");
    }

    @AfterMethod
    public void tearDown() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
    }
}