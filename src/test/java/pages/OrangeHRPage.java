package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.ConfigReader;
import utilities.Driver;

public class OrangeHRPage {
    WebDriver driver;

    public OrangeHRPage() {
        driver = Driver.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "txtUsername")
    public WebElement usernameInput;

    @FindBy(id = "txtPassword")
    public WebElement passwordInput;
    @FindBy(id = "btnLogin")
    public WebElement loginBtn;
    @FindBy(xpath="//*[@id=\"menu_admin_viewAdminModule\"]")
    public WebElement adminBtn;
    @FindBy(xpath="//a[@href=\"saveSystemUser?userId=10\"]")
    public WebElement employeeForEdit;
    @FindBy(id="btnSave")
    public WebElement editSaveBtn;
    @FindBy(id="systemUser_employeeName_empName")
    public WebElement emplName;
    @FindBy(id="systemUser_userName")
    public WebElement userName;
    @FindBy(id="systemUser_status")
    public WebElement dropdownStatus;
    @FindBy(id="systemUser_userType")
    public WebElement dropdownUserRole;

    @FindBy(id="systemUser_chkChangePassword")
    public WebElement passwordCheckBox;

    @FindBy(id="systemUser_password")
    public WebElement passwordBox;

    @FindBy(id="systemUser_confirmPassword")
    public WebElement confirmPasswordBox;

    @FindBy(xpath="//tr[@class=\"odd\"][1]")
    public WebElement firstRow;

    @FindBy(xpath="//span[contains(text(),'Employee does not exist')]")
    public WebElement errorEmplName;

    @FindBy(xpath="//span[contains(text(),'Should have at least 8 characters')]")
    public WebElement errorPassword;

    @FindBy(xpath="//span[contains(text(),'Please enter at least 8 characters.')]")
    public WebElement errorConfirmPassword;


    public void login() {
        driver.get(ConfigReader.getProperty("OrangeHRMURL"));
        usernameInput.sendKeys(ConfigReader.getProperty("OrangeHRMUsername"));
        passwordInput.sendKeys(ConfigReader.getProperty("OrangeHRMPassword"));
        loginBtn.click();
    }
}