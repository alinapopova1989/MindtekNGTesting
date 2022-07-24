package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.SauceDemoAppCheckout;
import pages.SauceDemoAppHomePage;
import pages.SauceDemoAppLoginPage;
import utilities.BrowserUtils;
import utilities.ConfigReader;
import utilities.TestBase;

import java.io.IOException;
import java.util.List;

public class SauceDemoAppTests extends TestBase {
    @Test(priority=1,groups = {"regression","smoke"})
    public void verifyLoginPositive(){
        //driver.get(ConfigReader.getProperty("SauceDemoURL"));

        SauceDemoAppLoginPage sdaLoginPage=new SauceDemoAppLoginPage();
        sdaLoginPage.login();

        WebElement titleProducts= driver.findElement(By.xpath("//span[contains(text(), 'Products')]"));
        Assert.assertTrue(titleProducts.isDisplayed());

    }

    @Test(priority=2,groups = {"regression","smoke", "itemFilter"})
    public void verifyPriceHighToLow() throws IOException {
        //driver.get(ConfigReader.getProperty("SauceDemoURL"));
        SauceDemoAppLoginPage sdaLoginPage = new SauceDemoAppLoginPage();
        sdaLoginPage.login();
        BrowserUtils.takeScreenshot("saucedemotest");

        Select filterDropdown = new Select(driver.findElement(By.tagName("select")));
        filterDropdown.selectByIndex(3);
        List<WebElement> itemPrices = driver.findElements(By.xpath("//div[@class='inventory_item_price']"));
        for (WebElement price : itemPrices) {
            System.out.println(price.getText());

            for (int i = 1; i < itemPrices.size(); i++) {

                Double itemPrice1 = Double.parseDouble(itemPrices.get(i - 1).getText().substring(1));
                Double itemPrice2 = Double.parseDouble(itemPrices.get(i).getText().substring(1));
                System.out.println(itemPrice1 + " is greater than " + itemPrice2);

                Assert.assertTrue(itemPrice1 >= itemPrice2);
            }
        }
    }
    @Test(priority=3,groups = {"regression","smoke","healthcheck", "daily", "totalPrice"})
            public void verifytotalPrice() {
                SauceDemoAppLoginPage sdaLoginPage = new SauceDemoAppLoginPage();
                sdaLoginPage.login();


                SauceDemoAppHomePage sdaHomeP=new SauceDemoAppHomePage();
                sdaHomeP.backpackBtn.click();
                sdaHomeP.bikeLightBtn.click();
                sdaHomeP.cartBtn.click();
                sdaHomeP.checkoutBtn.click();

                SauceDemoAppCheckout sdaCheckout=new SauceDemoAppCheckout();
                sdaCheckout.checkoutWithValidInfo();


                double expectedSubtotal=0.0;
                for(int i=0;i<sdaCheckout.itemPrices.size()-1;i++){
                    double price1=Double.parseDouble(sdaCheckout.itemPrices.get(i).getText().substring(1));
                    double price2=Double.parseDouble(sdaCheckout.itemPrices.get(i+1).getText().substring(1));
                    expectedSubtotal=price1+price2;
                }
                String subtotalPrice=sdaCheckout.subtotal.getText();
                double actualSubtotal=Double.parseDouble(subtotalPrice.substring(subtotalPrice.indexOf("$")+1));

                Assert.assertEquals(actualSubtotal,expectedSubtotal);
            }
        }
        


