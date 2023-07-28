package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import lib.ui.*;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class CoreTestCase {

    protected AppiumDriver<WebElement> driver;
    String url = "http://127.0.0.1:4723/wd/hub";
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

        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("deviceName","and80");
        capabilities.setCapability("platformVersion","8.1.0");
        capabilities.setCapability("automationName","Appium");
        capabilities.setCapability("appPackage","org.wikipedia");
        capabilities.setCapability("appActivity",".main.MainActivity");
        capabilities.setCapability("deviceOrientation", "portrait");
        capabilities.setCapability("app","C:\\Users\\KGrigorchuk\\Desktop\\mobile app automator\\JavaAppiumAutomation\\JavaAppiumAutomation\\apks\\org.wikipedia.apk");

        driver = new AndroidDriver<>(new URL(url), capabilities);
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
