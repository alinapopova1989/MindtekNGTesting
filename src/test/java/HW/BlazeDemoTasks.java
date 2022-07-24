package HW;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.TestBase;



public class BlazeDemoTasks extends TestBase {

    @Test
    public void verifyInfo() {
        driver.get(ConfigReader.getProperty("BlazeDemoURL"));

        String myLocation = "San Diego";
        String myDestination = "New York";
        String name = "David Clark";
        String address = "123 Wachington ave.";
        String city = "Austin";
        String state = "TX";
        String zipCode = "12345";
        String cardNum = "mycreditcard";
        String month = "11";
        String year = "2025";
        String nameCard = "David Clark";

        Select fromPort = new Select(driver.findElement(By.name("fromPort")));
        Select toPort = new Select(driver.findElement(By.name("toPort")));
        WebElement findFlightBtn = driver.findElement(By.xpath("//input[@value='Find Flights']"));

        fromPort.selectByValue(myLocation);
        toPort.selectByValue(myDestination);
        findFlightBtn.click();

        driver.findElement(By.xpath("/html/body/div[2]/table/tbody/tr[1]/td[1]/input")).click();

        WebElement nameBox = driver.findElement(By.id("inputName"));
        nameBox.sendKeys(name);
        WebElement addressBox = driver.findElement(By.id("address"));
        addressBox.sendKeys(address);
        WebElement cityBox = driver.findElement(By.id("city"));
        cityBox.sendKeys(city);
        WebElement stateBox = driver.findElement(By.id("state"));
        stateBox.sendKeys(state);
        WebElement zipBox = driver.findElement(By.id("zipCode"));
        zipBox.sendKeys(zipCode);
        Select cardTypeBox = new Select(driver.findElement(By.id("cardType")));
        cardTypeBox.selectByVisibleText("American Express");
        WebElement ccnBox = driver.findElement(By.id("creditCardNumber"));
        ccnBox.sendKeys(cardNum);
        WebElement monthBox = driver.findElement(By.id("creditCardMonth"));
        monthBox.sendKeys(month);
        WebElement yearBox = driver.findElement(By.id("creditCardYear"));
        yearBox.sendKeys(year);
        WebElement nameCardBox = driver.findElement(By.id("nameOnCard"));
        nameCardBox.sendKeys(nameCard);
        driver.findElement(By.xpath("//input[@type=\"submit\"]")).click();
        WebElement actualMsg = driver.findElement(By.tagName("h1"));

        Assert.assertEquals(actualMsg.getText(), "Invalid credit card number!");

            /*List<WebElement> prices = driver.findElements(By.xpath("/html/body/div[2]/table/tbody/tr/td[6]"));

                for (int i = 1; i < prices.size(); i++) {
                    Double newPrice = Double.parseDouble(prices.get(i - 1).getText().substring(1));
                    Double newPrice1 = Double.parseDouble(prices.get(i).getText().substring(1));

                    System.out.println(newPrice);
                    System.out.println(newPrice1);
                    //if (newPrice < newPrice1) {
                        */

    }
@Test
public void verifyCardInfo() {
    driver.get(ConfigReader.getProperty("BlazeDemoURL"));

    String myLocation = "San Diego";
    String myDestination = "New York";
    String name="John Doe";
    String address="123 Wachington ave.";
    String city="New York";
    String state="NY";
    String zipCode="12345";
    String cardNum="1234567890";
    String month="11";
    String year="2025";
    String nameCard="John Doe";
    String date="Tue, 12 Jul 2022";

    Select fromPort = new Select(driver.findElement(By.name("fromPort")));
    Select toPort = new Select(driver.findElement(By.name("toPort")));
    WebElement findFlightBtn = driver.findElement(By.xpath("//input[@value='Find Flights']"));

    fromPort.selectByValue(myLocation);
    toPort.selectByValue(myDestination);
    findFlightBtn.click();

    driver.findElement(By.xpath("/html/body/div[2]/table/tbody/tr[1]/td[1]/input")).click();

    WebElement nameBox= driver.findElement(By.id("inputName"));
    nameBox.sendKeys(name);
    WebElement addressBox= driver.findElement(By.id("address"));
    addressBox.sendKeys(address);
    WebElement cityBox=driver.findElement(By.id("city"));
    cityBox.sendKeys(city);
    WebElement stateBox=driver.findElement(By.id("state"));
    stateBox.sendKeys(state);
    WebElement zipBox=driver.findElement(By.id("zipCode"));
    zipBox.sendKeys(zipCode);
    Select cardTypeBox=new Select(driver.findElement(By.id("cardType")));
    cardTypeBox.selectByVisibleText("Visa");
    WebElement ccnBox=driver.findElement(By.id("creditCardNumber"));
    ccnBox.sendKeys(cardNum);
    WebElement monthBox=driver.findElement(By.id("creditCardMonth"));
    monthBox.sendKeys(month);
    WebElement yearBox=driver.findElement(By.id("creditCardYear"));
    yearBox.sendKeys(year);
    WebElement nameCardBox=driver.findElement(By.id("nameOnCard"));
    nameCardBox.sendKeys(nameCard);
    driver.findElement(By.xpath("//input[@type=\"submit\"]")).click();
    WebElement actualMsg=driver.findElement(By.tagName("h1"));
    Assert.assertEquals(actualMsg.getText(),"Thank you for your purchase today!");
    WebElement ccnActual=driver.findElement(By.xpath("//td[contains(text(),'xxxxxxxxxxxx1111')]"));
    WebElement actualDate=driver.findElement(By.xpath("//td[contains(text(),'0000')]"));
    Assert.assertTrue(actualDate.getText().contains(date));
    Assert.assertEquals(ccnActual.getText(), "xxxxxxxxxxxx7890");




}


        }
