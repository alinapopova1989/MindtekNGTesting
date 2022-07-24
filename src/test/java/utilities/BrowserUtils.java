package utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.Random;

public class BrowserUtils {


        public static String getRandomEmail(){
            String email="userEmail";
            Random random = new Random();
            int number = random.nextInt(1000);
            return email+number+"@gmail.com";

        }
        public static void SelectDropDownByValue(WebElement element, String value){
            Select select=new Select(element);
            select.selectByValue(value);
        }
//        this method waits for element to be clickable
    public static WebElement waitForElementsClickable(WebElement element, int seconds){
        WebDriverWait wait=new WebDriverWait(Driver.getDriver(),seconds);
        WebElement element1=wait.until(ExpectedConditions.elementToBeClickable(element));
        return element1;

    }
    //waits for element to be visible
    public static WebElement waitForElementsVisible(WebElement element, int seconds) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), seconds);
        WebElement element1 = wait.until(ExpectedConditions.visibilityOf(element));
        return element1;
    }
    //this methos gets screenshot
    public static void takeScreenshot(String testName) throws IOException {
        WebDriver driver=Driver.getDriver();
        File screenshot=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        String path="src/test/resources/screenshots/"+testName+".png";
        File file=new File(path);
        FileUtils.copyFile(screenshot,file);
    }
    }

