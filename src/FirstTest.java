
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

public class FirstTest {

    private AppiumDriver driver;
    public static final long DEFAULT_WAIT_TIME = 15;
    public static final String ERROR_MESSAGE = "Element not found";

    @Before
    public void setUp() throws Exception
    {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("deviceName","AndroidTestDevice");
        capabilities.setCapability("platformVersion","8.1.0");
        capabilities.setCapability("automationName","Appium");
        capabilities.setCapability("appPackage","org.wikipedia");
        capabilities.setCapability("appActivity",".main.MainActivity");
        capabilities.setCapability("app","C:\\Users\\KGrigorchuk\\Desktop\\mobile app automator\\JavaAppiumAutomation\\JavaAppiumAutomation\\apks\\org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

    }

    @After
    public void tearDown(){
        driver.quit();
    }

    @Test
    public void firstTest(){
        waitAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                ERROR_MESSAGE,
                DEFAULT_WAIT_TIME
        );

        waitAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Java",
                ERROR_MESSAGE,
                DEFAULT_WAIT_TIME
        );

        waitUntilElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']" +
                                "//*[contains(@text,'Object-oriented programming language')]"
                ),
                "Current article not found",
                DEFAULT_WAIT_TIME);
    }

    @Test
    public void testCancelSearch(){
        waitAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find search input",
                DEFAULT_WAIT_TIME
        );

        waitAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Cannot find X to cancel search",
                DEFAULT_WAIT_TIME
        );

        waitElementNotPresent(
                By.id("org.wikipedia:id/search_close_btn"),
                "X is still present on the page",
                5
        );
    }

    private WebElement waitUntilElementPresent(By locator, String errorMsg, long timeoutInSeconds){
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(errorMsg + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    private boolean waitElementNotPresent(By locator, String errorMsg, long timeoutInSeconds){
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(errorMsg + "\n");
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    private WebElement waitAndClick(By locator, String errorMsg, long timeoutInSeconds){
        WebElement element = waitUntilElementPresent(locator, errorMsg, timeoutInSeconds);
        element.click();
        return element;
    }

    private WebElement waitAndSendKeys(By by, String value, String errorMsg, long timeoutInSeconds){
        WebElement element = waitUntilElementPresent(by, errorMsg, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }
    
}
