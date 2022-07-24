package project;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import pages.OrangeHRPage;
import utilities.BrowserUtils;
import utilities.ConfigReader;
import utilities.TestBase;

public class HRMOrange extends TestBase {
    public OrangeHRPage orange = new OrangeHRPage();
    @Test(priority = 1)//positive
    public void verifyEditInfo() throws InterruptedException {


        orange.login();
        orange.adminBtn.click();
        orange.employeeForEdit.click();
        orange.editSaveBtn.click();

        Select userRole = new Select(orange.dropdownUserRole);
        userRole.selectByValue("1");

        orange.emplName.click();
        orange.emplName.clear();
        orange.emplName.sendKeys("Garry White");

        orange.userName.click();
        orange.userName.clear();
        orange.userName.sendKeys("Aaruba");


        Select status = new Select(orange.dropdownStatus);
        status.selectByValue("0");

        orange.passwordCheckBox.click();
        orange.passwordBox.sendKeys("admin123");
        orange.confirmPasswordBox.sendKeys("admin123");
        orange.editSaveBtn.click();

        Thread.sleep(3000);

        Assert.assertTrue(orange.firstRow.getText().contains("Aaruba Admin Garry White Disabled"));
    }
        @Test(priority = 2)//negative
        public void passwordAcceptance() throws InterruptedException {

        orange.login();
            orange.adminBtn.click();
            orange.employeeForEdit.click();
            orange.editSaveBtn.click();
            orange.passwordCheckBox.click();
            orange.passwordBox.sendKeys("admin");
            Assert.assertEquals(orange.errorPassword.getText(), "Should have at least 8 characters");
            orange.confirmPasswordBox.sendKeys("admin");
            Assert.assertEquals(orange.errorConfirmPassword.getText(), "Please enter at least 8 characters.");

        }
        @Test(priority = 3)
    public void employeeNameAcceptance() throws InterruptedException {

            orange.login();
            orange.adminBtn.click();
            orange.employeeForEdit.click();
            orange.editSaveBtn.click();
            orange.emplName.click();
            orange.emplName.clear();
            orange.emplName.sendKeys("John Doe");
            Assert.assertEquals(orange.errorEmplName.getText(),"Employee does not exist");


    }

    }
