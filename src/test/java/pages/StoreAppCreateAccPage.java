package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class StoreAppCreateAccPage {
    WebDriver driver;

    public StoreAppCreateAccPage() {
        driver = Driver.getDriver();
        PageFactory.initElements(driver, this);
    }
        @FindBy(id="customer_firstname")
        public WebElement firstNameInput;

    @FindBy(id="customer_lastname")
    public WebElement lastNameInput;
    @FindBy(id="passwd")
    public WebElement passwordInput;
    @FindBy(id="days")
    public WebElement dropdownDays;
    @FindBy(id="months")
    public WebElement dropdownMonths;
    @FindBy(id="years")
    public WebElement dropdownYears;

    @FindBy(id="address1")
    public WebElement addressInput;
    @FindBy(id="city")
    public WebElement cityInput;
    @FindBy(id="id_state")
    public WebElement dropdownState;

    @FindBy(id="postcode")
    public WebElement zipInput;
    @FindBy(id="phone_mobile")
    public WebElement phoneInput;

    @FindBy(id="alias")
    public WebElement aliasInput;
    @FindBy(id="submitAccount")
    public WebElement submitBtn;





}