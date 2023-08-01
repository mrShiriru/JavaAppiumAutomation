package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import lib.ui.*;
import org.junit.After;
import org.junit.Before;
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

    @Before
    public void setUp() throws Exception
    {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        /**
         * {
         *   "platformName": "iOS",
         *   "appium:platformVersion": "14.3",
         *   "appium:deviceName": "MyIPhone12",
         *   "appium:app": "/Users/user/Desktop/javaApiumAutomation/javaAppiumAutomation/apks/wikipedia.app",
         *   "appium:automationName": "XCUITest"
         * }
         */
        capabilities.setCapability("deviceOrientation", "portrait");
        capabilities.setCapability("platformName","iOS");
        capabilities.setCapability("deviceName","My iPhone SE");
        capabilities.setCapability("platformVersion","14.3");
        capabilities.setCapability("automationName", "XCUITest");
        capabilities.setCapability("app","/Users/user/Desktop/javaApiumAutomation/JavaAppiumAutomation/apks/wikipedia.app");
        driver = new IOSDriver(new URL(url), capabilities);
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

    @After
    public void tearDown(){
        driver.quit();
    }

}
