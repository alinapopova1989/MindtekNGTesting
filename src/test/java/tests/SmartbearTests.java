package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class SmartbearTests {
    WebDriver driver;
    String username="Tester";
    String password="test";
    String expectedWelcomeText="Welcome, Tester!";
    String expectedInvalidMsg="Invalid Login or Password.";
    WebElement usernameBox;
    WebElement passwordbox;
    WebElement loginBtn;



    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("http://secure.smartbearsoftware.com/samples/testcomplete11/WebOrders/login.aspx");
        usernameBox=driver.findElement(By.id("ctl00_MainContent_username"));
        passwordbox=driver.findElement(By.name("ctl00$MainContent$password"));
        loginBtn= driver.findElement(By.name("ctl00$MainContent$login_button"));
    }
    @Test(groups={"regression"})
    public void loginPositive(){

        usernameBox.sendKeys(username);
        passwordbox.sendKeys(password);
        loginBtn.click();
        String actualWelcomeMsg=driver.findElement(By.xpath("//form[@id='aspnetForm']//div[@class='login_info']"))
        .getText();
        Assert.assertEquals(actualWelcomeMsg.substring(0,actualWelcomeMsg.indexOf('!')+1).trim(),expectedWelcomeText);
    }
    @Test(priority=1)
    public void loginNegative(){

        usernameBox.sendKeys("Alina");
        passwordbox.sendKeys("1234");
        loginBtn.click();
        String actualInvalidMsg=driver.findElement(By.id("ctl00_MainContent_status")).getText();
        Assert.assertEquals(actualInvalidMsg,expectedInvalidMsg);
    }
    @Test(priority=2)
    public void loginEmptyCredentials(){
        usernameBox.sendKeys("");
        passwordbox.sendKeys("");
        loginBtn.click();
        String actualInvalidMsg=driver.findElement(By.id("ctl00_MainContent_status")).getText();
        Assert.assertEquals(actualInvalidMsg,expectedInvalidMsg);
    }
    @Test(priority=3)
    public void totalAmountPos() throws InterruptedException {
        loginPositive();
        WebElement orderBtn=driver.findElement(By.xpath("//a[@href=\"Process.aspx\"]"));
        orderBtn.click();

        Select product=new Select(driver.findElement(By.id("ctl00_MainContent_fmwOrder_ddlProduct")));
        WebElement quantityField=driver.findElement(By.id("ctl00_MainContent_fmwOrder_txtQuantity"));
        WebElement calculate=driver.findElement(By.xpath("//input[@value=\"Calculate\"]"));
        WebElement totalAmount= driver.findElement(By.id("ctl00_MainContent_fmwOrder_txtTotal"));

        //WebDriverWait wait=new WebDriverWait(driver,10);
        //wait.until(ExpectedConditions.)




        Integer quantity=4;
        Integer price=100;
        product.selectByVisibleText("MyMoney");
        quantityField.click();
        quantityField.sendKeys(Keys.BACK_SPACE+quantity.toString());
        calculate.click();
        Thread.sleep(4000);

        Integer expectedAmount=quantity*price;

        Assert.assertEquals(totalAmount.getText(),expectedAmount.toString());

    }
    @AfterMethod
    public void tearDown(){
        driver.quit();

    }
}
