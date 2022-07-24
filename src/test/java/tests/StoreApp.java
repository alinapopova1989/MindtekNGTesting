package tests;

import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.StoreAppAccountPage;
import pages.StoreAppCreateAccPage;
import pages.StoreAppHomepage;
import pages.StoreAppLoginPage;
import utilities.BrowserUtils;
import utilities.ConfigReader;
import utilities.TestBase;

import java.util.Random;

public class StoreApp extends TestBase {

    StoreAppHomepage homePage = new StoreAppHomepage();
    StoreAppLoginPage loginPage = new StoreAppLoginPage();
    StoreAppCreateAccPage createPage = new StoreAppCreateAccPage();
    String email;
    String password;
    String firstName;
    String lastName;
    String address;
    String city;

    @DataProvider(name = "signUpTestData")
    public static Object[][] testData() {
        return new Object[][]{
                {"Jane", "Doe", "123Pass", "1", "6", "1991", "123 Buffalo St", "Chicago", "13", "12345", "9876543215", "MyAlies"},
                //{"John", "Hash", "2222Pass", "2", "7", "1998", "222 Golf St", "New York", "15", "54321", "654738291", "Alies"},
        };
    }

    @Test(groups = {"regression"}, dataProvider = "signUpTestData")
    public void createAccount(
            String firstName,
            String lastName,
            String password,
            String day,
            String month,
            String year,
            String address,
            String city,
            String state,
            String postcose,
            String phoneNumber,
            String alias
    ) {

        driver.get(ConfigReader.getProperty("StoreAppURL"));
        homePage.signInBtn.click();
        email=BrowserUtils.getRandomEmail();
        loginPage.createEmailInput.sendKeys(email);
        loginPage.createAccBtn.click();

        this.firstName=firstName;
        createPage.firstNameInput.sendKeys(firstName);
        this.lastName=lastName;
        createPage.lastNameInput.sendKeys(lastName);
        this.password=password;
        createPage.passwordInput.sendKeys(password);

        BrowserUtils.SelectDropDownByValue(createPage.dropdownDays, day);
        BrowserUtils.SelectDropDownByValue(createPage.dropdownMonths, month);
        BrowserUtils.SelectDropDownByValue(createPage.dropdownYears, year);

        this.address=address;
        createPage.addressInput.sendKeys(address);
        this.city=city;
        createPage.cityInput.sendKeys(city);

        BrowserUtils.SelectDropDownByValue(createPage.dropdownState, state);

        createPage.zipInput.sendKeys(postcose);
        createPage.phoneInput.sendKeys(phoneNumber);
        createPage.aliasInput.sendKeys(alias);

        createPage.submitBtn.click();
    }

    @Test(groups = {"regression"}, dependsOnMethods ="createAccount")
    public void sighInTest(){
        StoreAppHomepage homePage = new StoreAppHomepage();
        StoreAppLoginPage loginPage = new StoreAppLoginPage();
        StoreAppAccountPage accPage=new StoreAppAccountPage();

        driver.get(ConfigReader.getProperty("StoreAppURL"));
        homePage.signInBtn.click();

        loginPage.emailInput.sendKeys(email);
        loginPage.passwordInput.sendKeys(password);
        loginPage.sighInButton.click();
        String expectedTitle="My account - My Store";
        String actualTitle=driver.getTitle();
         accPage.myAddress.click();

        Assert.assertEquals(actualTitle,expectedTitle);

        SoftAssert softAssert=new SoftAssert();
        softAssert.assertEquals(actualTitle,expectedTitle);
        softAssert.assertEquals(accPage.firstName.getText(),firstName);
        softAssert.assertEquals(accPage.lastName.getText(),lastName);
        softAssert.assertEquals(accPage.address.getText(),address);
        softAssert.assertEquals(accPage.city.getText().replace(",", " ").trim(),city);

        softAssert.assertAll();//more then 2 ->soft assert

}
}