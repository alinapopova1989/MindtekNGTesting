package HW;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Weatherbase {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        //ChromeOptions options = new ChromeOptions();
        //options.addArguments("--headless");
        //options.addArguments("disable-popup-blocking");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("http://www.weatherbase.com/");


    }

    @Test
    public void zipVerification() {
        WebElement searchBox = driver.findElement(By.id("query"));
        searchBox.clear();
        searchBox.click();
        searchBox.sendKeys("60656");
        WebElement searchBtn = driver.findElement(By.xpath("//button[@type=\"submit\"]"));
        searchBtn.click();
        String actualResult = driver.findElement(By.xpath("//a[@onclick=\"if (!window.__cfRLUnblockHandlers) return false; " +
                "recordOutboundLink(this, 'ZIP Search'" +
                ", 'Chicago, Illinois, United States of America'); return false;\"]")).getText();
        Assert.assertEquals(actualResult, "Chicago, Illinois");
    }
    @Test
    public void invalidZip(){
        WebElement searchBox = driver.findElement(By.id("query"));
        searchBox.clear();
        searchBox.click();
        searchBox.sendKeys("1234");
        WebElement searchBtn = driver.findElement(By.xpath("//button[@type=\"submit\"]"));
        searchBtn.click();
        String actualResult=driver.findElement(By.xpath("//p[contains(text()," +
                "\"We're sorry. Your search for 1234 returned no results" +
                ". Please try the following:\")]")).getText();
        Assert.assertEquals(actualResult,"We're sorry. Your search for 1234 returned no results. Please try the following:");
    }

    @Test
    public void validation50States() throws InterruptedException {
        driver.findElement(By.xpath("//div[@id=\"left-170\"]/ul/li[11]")).click();

        Thread.sleep(2000);

        driver.findElement(By.xpath("//a[@href=\"/weather/state.php3?c=US&name=United-States-of-America\"]")).click();

        List<WebElement> allStates = driver.findElements(By.xpath("//ul/li"));


        Assert.assertTrue(allStates.size() >= 50);

    }
    @Test
    public void temperatureValidation() throws InterruptedException {
        driver.findElement(By.xpath("//div[@id=\"left-170\"]/ul/li[11]")).click();
        Thread.sleep(2000);

        driver.findElement(By.xpath("//a[@href=\"/weather/state.php3?c=US&name=United-States-of-America\"]")).click();
        driver.findElement(By.xpath("//a[@onclick=\"if (!window.__cfRLUnblockHandlers) " +
                "return false; recordOutboundLink(this, 'Browse - State', 'California'); return false;\"]")).click();
        driver.findElement(By.xpath("//a[@onclick=\"if (!window.__cfRLUnblockHandlers) return false; recordOutboundLink(this, 'Browse - California', 'San Jose'); return false;\"]")).click();
        driver.findElement(By.xpath("//a[@onclick=\"if (!window.__cfRLUnblockHandlers) return false; recordOutboundLink(this, 'Weather Data', 'US')\"]")).click();

        List<WebElement> table=driver.findElements(By.xpath("//tr[@bgcolor=\"white\"]/td[@class=\"dataunit\"]"));
        for(WebElement t:table){
            if(t.getText().equalsIgnoreCase("C")){
                System.out.println("pass");

            }else{
                System.out.println("fail");
            }
        }
    }
    @Test
    public void temperatureValidationF() throws InterruptedException {
        driver.findElement(By.xpath("//div[@id=\"left-170\"]/ul/li[11]")).click();
        //Thread.sleep(2000);
        driver.findElement(By.xpath("//a[@href=\"/weather/state.php3?c=US&name=United-States-of-America\"]")).click();
        driver.findElement(By.xpath("//a[@onclick=\"if (!window.__cfRLUnblockHandlers) " +
                "return false; recordOutboundLink(this, 'Browse - State', 'California'); return false;\"]")).click();
        driver.findElement(By.xpath("//a[@onclick=\"if (!window.__cfRLUnblockHandlers) return false; recordOutboundLink(this, 'Browse - California', 'San Jose'); return false;\"]")).click();
        driver.findElement(By.xpath("//a[@onclick=\"if (!window.__cfRLUnblockHandlers) return false; recordOutboundLink(this, 'Weather Data', 'US')\"]")).click();
        List<WebElement> table = driver.findElements(By.xpath("//tr[@bgcolor=\"white\"]/td[@class=\"dataunit\"]"));

            System.out.println(table.get(2).getText());
            Integer tempC=Integer.valueOf(table.get(2).getText());
            Integer expectedResultInF=(tempC*9/5)+32;
            String finalResultInF= expectedResultInF.toString();

        driver.findElement(By.xpath("//a[@onclick=\"if (!window.__cfRLUnblockHandlers) " +
                "return false; recordOutboundLink(this, 'Weather Data', 'Metric')\"]")).click();
        List<WebElement> tableNext = driver.findElements(By.xpath("//tr[@bgcolor=\"white\"]/td[@class=\"dataunit\"]"));
        String actualResult=tableNext.get(2).getText();
        Assert.assertEquals(finalResultInF,actualResult);

    }
    @AfterMethod
    public void tearDown() {
        driver.quit();
}
}
