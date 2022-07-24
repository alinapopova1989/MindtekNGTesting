package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Set;
import java.util.concurrent.TimeUnit;

public class WindowHandles {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://the-internet.herokuapp.com/windows");
    }
    @Test
    public void windowHandleVerif(){
        WebElement newWindowLink=driver.findElement(By.xpath("//a[@href=\"/windows/new\"]"));
        String mainWindowId=driver.getWindowHandle();
        newWindowLink.click();

        Set<String> windowIds=driver.getWindowHandles();
        for(String windowID:windowIds){
            if(!windowID.equalsIgnoreCase(mainWindowId)){
                driver.switchTo().window(windowID);
            }
        }
        String newwindowText=driver.findElement(By.tagName("h3")).getText();
        Assert.assertEquals(newwindowText,"New Window");
        driver.close();

    }
    @AfterMethod
    public void teatDown() {
        driver.quit();
    }
}