package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import lib.ui.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class CoreTestCase {

    protected AppiumDriver driver;
    String url = "http://127.0.0.1:4723/";
    public static final long
            DEFAULT_WAIT_TIME = 15,
            SHORT_WAIT_TIME = 5;
    public static final String ERROR_MESSAGE = "Element not found";

    public MainPage mainPage;
    public SearchPage searchPage;
    public ArticlePage articlePage;
    public SavedPage savedPage;
    public GroupPage groupPage;


    @BeforeEach
    public void setUp() throws Exception
    {
        DesiredCapabilities iosCaps = new DesiredCapabilities();
        iosCaps.setCapability("deviceOrientation", "portrait");
        iosCaps.setCapability("platformName", "iOS");
        iosCaps.setCapability("deviceName", "My iPhone SE");
        iosCaps.setCapability("platformVersion", "14.3");
        iosCaps.setCapability("automationName", "XCUITest");
        iosCaps.setCapability("app", "/Users/user/Desktop/JavaAppiumAutomation/apks/Wikipedia.app");
        driver =  new IOSDriver(new URL(url), iosCaps);
        loadingPage();
        mainPage.skipOnboarding();
    }

    private void loadingPage(){
        searchPage = new SearchPage(driver);
        articlePage = new ArticlePage(driver);
        savedPage = new SavedPage(driver);
        groupPage = new GroupPage(driver);
        mainPage = new MainPage(driver);
    }

    @AfterEach
    public void tearDown(){
        driver.quit();
    }

}
