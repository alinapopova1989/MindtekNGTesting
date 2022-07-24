package project;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Project1 {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();

    }

    @Test
    public void checkLogin() {
        driver.get("https://qeenv-webhr-arslan.herokuapp.com/");
        String login = "Mindtek";
        String password = "MindtekStudent";

        WebElement loginInput = driver.findElement(By.name("username"));
        WebElement passwordInput = driver.findElement(By.name("password"));
        WebElement loginBtn = driver.findElement(By.xpath("//button[@class=\"btn btn-success\"]"));

        loginInput.sendKeys("Mindtek");
        passwordInput.sendKeys("MindtekStudent");
        loginBtn.click();
    }

    @Test
    public void createEmployee() {

        checkLogin();
        WebElement createEmployeeBtn = driver.findElement(By.xpath("//a[@href=\"/employee/-1\"]"));
        createEmployeeBtn.click();


        WebElement firstNameBox = driver.findElement(By.xpath("//input[@name=\"firstName\"]"));

        firstNameBox.sendKeys("abcdefg");


        WebElement lastNameBox = driver.findElement(By.xpath("//input[@name=\"lastName\"]"));
        lastNameBox.sendKeys("abcdefg");

        Select select = new Select(driver.findElement(By.id("department")));
        select.selectByValue("60");

        WebElement salaryBox = driver.findElement(By.xpath("//input[@name=\"salary\"]"));
        salaryBox.sendKeys("100000");


        WebElement saveBtn = driver.findElement(By.xpath("//button[@class=\"btn btn-success\"]"));
        saveBtn.click();

    }

    @Test
    public void logOut() {
        checkLogin();
        WebElement loggOut = driver.findElement(By.xpath("//a[@routerlink=\"/logout\"]"));
        loggOut.click();
        String expectedResult = "You are ligged out";
        String actualResult = driver.findElement(By.tagName("h1")).getText();

        Assert.assertEquals(expectedResult, actualResult, " Logged out paased");
    }

    @Test
    public void employeeEdit() {//positive test case
        checkLogin();
        WebElement editBtn = driver.findElement(By.xpath("/html/body/app-root/div[1]/app-employee-details/div[3]/table/tbody[2]/tr/td[5]/button"));
        editBtn.click();
        WebElement fNInput = driver.findElement(By.name("firstName"));
        WebElement lNInput = driver.findElement(By.name("lastName"));
        String firstName = "Ali";
        String lastName = "Muh";
        fNInput.clear();
        fNInput.sendKeys(firstName);
        lNInput.clear();
        lNInput.sendKeys(lastName);
        WebElement saveBtn = driver.findElement(By.xpath("//button[@type='submit']"));
        saveBtn.click();

        List<WebElement> nameText = driver.findElements(By.xpath("//tbody[2]"));
        for (int i = 0; i < nameText.size(); i++) {
            Assert.assertTrue(nameText.get(i).getText().contains(firstName) && nameText.get(i).getText().contains(lastName));
        }
    }

    @Test
    public void employeeEdit2() {//negative testcase
        checkLogin();
        WebElement editBtn = driver.findElement(By.xpath("/html/body/app-root/div[1]/app-employee-details/div[3]/table/tbody[2]/tr/td[5]/button"));
        editBtn.click();
        WebElement fNInput = driver.findElement(By.name("firstName"));
        WebElement lNInput = driver.findElement(By.name("lastName"));
        String firstName = "JZ";
        String lastName = "OG";
        fNInput.clear();
        fNInput.sendKeys(firstName);
        lNInput.clear();
        lNInput.sendKeys(lastName);
        WebElement saveBtn = driver.findElement(By.xpath("//button[@type='submit']"));
        saveBtn.click();

        List<WebElement> nameText = driver.findElements(By.xpath("//tbody[2]"));
        for (int i = 0; i < nameText.size(); i++) {
            Assert.assertTrue(nameText.get(i).getText().contains(firstName) && nameText.get(i).getText().contains(lastName));
        }
    }

    @Test
    public void employeeEdit3() {//negative t
        checkLogin();
        WebElement editBtn = driver.findElement(By.xpath("/html/body/app-root/div[1]/app-employee-details/div[3]/table/tbody[2]/tr/td[5]/button"));
        editBtn.click();
        WebElement fNInput = driver.findElement(By.name("firstName"));
        WebElement lNInput = driver.findElement(By.name("lastName"));
        String firstName = "Patel%";
        String lastName = "Harsh**";
        fNInput.clear();
        fNInput.sendKeys(firstName);
        lNInput.clear();
        lNInput.sendKeys(lastName);
        WebElement saveBtn = driver.findElement(By.xpath("//button[@type='submit']"));
        saveBtn.click();

        List<WebElement> nameText = driver.findElements(By.xpath("//tbody[2]"));
        for (int i = 0; i < nameText.size(); i++) {
            Assert.assertTrue(nameText.get(i).getText().contains(firstName) && nameText.get(i).getText().contains(lastName));
        }

    }
    @Test
    public void checkSalary() {//positive
        checkLogin();
        WebElement editBtn = driver.findElement(By.xpath("/html/body/app-root/div[1]/app-employee-details/div[3]/table/tbody[2]/tr/td[5]/button"));
        editBtn.click();
        WebElement fNInput = driver.findElement(By.name("firstName"));
        WebElement lNInput = driver.findElement(By.name("lastName"));
        String firstName = "Patel";
        String lastName = "Harsh";
        String salary = "1000";
        fNInput.clear();
        fNInput.sendKeys(firstName);
        lNInput.clear();
        lNInput.sendKeys(lastName);

        WebElement salaryBox = driver.findElement(By.name("salary"));
        salaryBox.clear();
        salaryBox.sendKeys(salary);
        WebElement saveBtn = driver.findElement(By.xpath("//button[@type='submit']"));
        saveBtn.click();

        List<WebElement> nameText = driver.findElements(By.xpath("//tbody[2]"));

        driver.findElement(By.xpath("/html/body/app-root/div[1]/app-employee-details/div[3]/table/tbody[2]/tr/td[5]/button")).click();
        JavascriptExecutor jse = ((JavascriptExecutor) driver);
        jse.executeScript("window.scrollBy(0,200)");
        String validatedSalary = driver.findElement(By.name("salary")).getText();

        Assert.assertEquals(validatedSalary, salary);
    }
    @Test
    public void checkSalary2() {//negative
        checkLogin();
        WebElement editBtn = driver.findElement(By.xpath("/html/body/app-root/div[1]/app-employee-details/div[3]/table/tbody[2]/tr/td[5]/button"));
        editBtn.click();
        WebElement fNInput = driver.findElement(By.name("firstName"));
        WebElement lNInput = driver.findElement(By.name("lastName"));
        String firstName = "Patel";
        String lastName = "Harsh";
        String salary = "1";
        fNInput.clear();
        fNInput.sendKeys(firstName);
        lNInput.clear();
        lNInput.sendKeys(lastName);

        WebElement salaryBox = driver.findElement(By.name("salary"));
        salaryBox.clear();
        salaryBox.sendKeys(salary);
        WebElement saveBtn = driver.findElement(By.xpath("//button[@type='submit']"));
        saveBtn.click();

        List<WebElement> nameText = driver.findElements(By.xpath("//tbody[2]"));

        for (int i = 0; i < nameText.size(); i++) {
            Assert.assertTrue(nameText.get(i).getText().contains(firstName));
        }
    }
@Test
    public void checkSalary3() {//negative
    checkLogin();
    WebElement editBtn = driver.findElement(By.xpath("/html/body/app-root/div[1]/app-employee-details/div[3]/table/tbody[2]/tr/td[5]/button"));
    editBtn.click();
    WebElement fNInput = driver.findElement(By.name("firstName"));
    WebElement lNInput = driver.findElement(By.name("lastName"));
    String firstName = "Patel";
    String lastName = "Harsh";
    String salary = "-1";
    fNInput.clear();
    fNInput.sendKeys(firstName);
    lNInput.clear();
    lNInput.sendKeys(lastName);

    WebElement salaryBox = driver.findElement(By.name("salary"));
    salaryBox.clear();
    salaryBox.sendKeys(salary);
    WebElement saveBtn = driver.findElement(By.xpath("//button[@type='submit']"));
    saveBtn.click();

    List<WebElement> nameText = driver.findElements(By.xpath("//tbody[2]"));

    for (int i = 0; i < nameText.size(); i++) {
        Assert.assertTrue(nameText.get(i).getText().contains(firstName));
    }
}
    @Test
    public void checkSalary4() {//negative
        checkLogin();
        WebElement editBtn = driver.findElement(By.xpath("/html/body/app-root/div[1]/app-employee-details/div[3]/table/tbody[2]/tr/td[5]/button"));
        editBtn.click();
        WebElement fNInput = driver.findElement(By.name("firstName"));
        WebElement lNInput = driver.findElement(By.name("lastName"));
        String firstName = "Patel";
        String lastName = "Harsh";
        String salary = "0";
        fNInput.clear();
        fNInput.sendKeys(firstName);
        lNInput.clear();
        lNInput.sendKeys(lastName);

        WebElement salaryBox = driver.findElement(By.name("salary"));
        salaryBox.clear();
        salaryBox.sendKeys(salary);
        WebElement saveBtn = driver.findElement(By.xpath("//button[@type='submit']"));
        saveBtn.click();

        List<WebElement> nameText = driver.findElements(By.xpath("//tbody[2]"));

        driver.findElement(By.xpath("/html/body/app-root/div[1]/app-employee-details/div[3]/table/tbody[2]/tr/td[5]/button")).click();
        JavascriptExecutor jse = ((JavascriptExecutor) driver);
        jse.executeScript("window.scrollBy(0,200)");
        String validatedSalary = driver.findElement(By.name("salary")).getText();

        Assert.assertEquals(validatedSalary, salary);
    }

        //for (int i = 0; i < nameText.size(); i++) {
        //    Assert.assertTrue(nameText.get(i).getText().contains(firstName));
        //}
    @Test
    public void checkDepartAndJobTitle() {//positive
        checkLogin();
        WebElement editBtn = driver.findElement(By.xpath("/html/body/app-root/div[1]/app-employee-details/div[3]/table/tbody[2]/tr/td[5]/button"));
        editBtn.click();
        WebElement fNInput = driver.findElement(By.name("firstName"));
        WebElement lNInput = driver.findElement(By.name("lastName"));
        String firstName = "Patel";
        String lastName = "Harsh";
        String salary = "1000";
        String depName = "IT";
        String jobTitle = "Programmer";
        fNInput.clear();
        fNInput.sendKeys(firstName);
        lNInput.clear();
        lNInput.sendKeys(lastName);

        WebElement salaryBox = driver.findElement(By.name("salary"));
        salaryBox.clear();
        salaryBox.sendKeys(salary);


        Select select = new Select(driver.findElement(By.id("department")));
        select.selectByVisibleText(depName);

        Select select2 = new Select(driver.findElement(By.id("job")));
        select2.selectByVisibleText(jobTitle);

        WebElement saveBtn = driver.findElement(By.xpath("//button[@type='submit']"));
        saveBtn.click();

        List<WebElement> nameText = driver.findElements(By.xpath("//tbody[2]"));
        for (int i = 0; i < nameText.size(); i++) {
            Assert.assertTrue(nameText.get(i).getText().contains(depName));
        }
    }

         @AfterMethod
        public void End() {
        driver.quit();
    }
}