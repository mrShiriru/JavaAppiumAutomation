import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.List;

public abstract class AbstractWebTest {

    private AppiumDriver<WebElement> driver;

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

        driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

    }

    @After
    public void tearDown(){
        driver.quit();
    }


    protected WebElement waitElementPresent(By locator, String errorMsg, long timeoutInSeconds){
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(errorMsg + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    protected List<WebElement> waitElementsPresent(By locator, String errorMsg, long timeoutInSeconds){
        return new WebDriverWait(driver, timeoutInSeconds)
                .withMessage(errorMsg + "\n")
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }

    protected boolean waitElementNotPresent(By locator, String errorMsg, long timeoutInSeconds){
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(errorMsg + "\n");
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    protected boolean waitElementsNotPresent(List<WebElement> elements, String errorMsg, long timeoutInSeconds){
        return new WebDriverWait(driver, timeoutInSeconds)
                .withMessage(errorMsg + "\n")
                .until(ExpectedConditions.invisibilityOfAllElements(elements));
    }

    protected WebElement waitAndClick(By locator, String errorMsg, long timeoutInSeconds){
        WebElement element = waitElementPresent(locator, errorMsg, timeoutInSeconds);
        element.click();
        return element;
    }

    protected WebElement waitAndSendKeys(By by, String value, String errorMsg, long timeoutInSeconds){
        WebElement element = waitElementPresent(by, errorMsg, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    protected List<WebElement> getElements(By locator) {
        return driver.findElements(locator);
    }

    protected void swipeUp(){
        TouchAction action = new TouchAction(driver);
        Dimension size =driver.manage().window().getSize();
        int x = size.getHeight()/2;
        int yStart = (int) (size.getWidth() * 0.8);
        int yEnd = (int) (size.getWidth() * 0.2);

        action.press(x,yStart).waitAction(1000).moveTo(x, yEnd).release().perform();
    }

}
