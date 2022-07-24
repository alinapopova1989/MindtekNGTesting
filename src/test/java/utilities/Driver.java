package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.concurrent.TimeUnit;


public class Driver {
    private static WebDriver driver;
    /**
     * this method sets up Driver
     * Name = getDriver();
     * @return WebDriver
     */
    public static WebDriver getDriver(){//this method to use to have single driver object all over test cases

        String browser=ConfigReader.getProperty("browser");
        if(driver==null || ((RemoteWebDriver)driver).getSessionId()==null){
            if(browser.equals("chrome")){
                WebDriverManager.chromedriver().setup();
                driver=new ChromeDriver();
            }else if(browser.equals("edge")){
                WebDriverManager.edgedriver().setup();
                driver=new EdgeDriver();
            }else if(browser.equals("firefox")){
                WebDriverManager.firefoxdriver().setup();
                driver=new FirefoxDriver();
            }else if(browser.equals("ie")) {
                WebDriverManager.iedriver().setup();
                driver = new InternetExplorerDriver();//sets up driver if it was not set up before
                //but if it was set up then it will return prev object
                //this way we keep single driver object in our framework

            }
        }else{
            return driver;
        }
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return driver;
    }
}
