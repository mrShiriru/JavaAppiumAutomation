import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import lib.ui.MainPage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class Ex7Test {

    protected AppiumDriver<WebElement> driver;
    MainPage mainPage;
    String url = "http://127.0.0.1:4723/wd/hub";

    /**
     * Один из вариантов решения проблемы, чтобы экран всегда оказывался в правильном положении, это
     * Добавить настройку при старте эмулятора, например вот так:
     *      DesiredCapabilities capabilities = new DesiredCapabilities();
     *      capabilities.setCapability("deviceOrientation", "portrait");
     */
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
        mainPage = new MainPage(driver);
        mainPage.skipOnboarding();
    }

    @Test
    public void testEx7_OrientationScreen() {
        boolean check_before = isCurrentOrientationPortrait();
        driver.rotate(ScreenOrientation.LANDSCAPE);
        boolean check_after = isCurrentOrientationPortrait();

        Assert.assertNotEquals("Screen orientation after rotation is the same", check_before, check_after);
    }

    private boolean isCurrentOrientationPortrait(){
        String ort = driver.getOrientation().name();
        ScreenOrientation orientation = ScreenOrientation.valueOf(ort);

        if (orientation == ScreenOrientation.PORTRAIT) {
            System.out.println("Current screen orientation is portrait");
            return true;

        } else {
            System.out.println("Current screen orientation is Landscape");
            return false;
        }
    }

    @After
    public void tearDown(){
        driver.quit();
    }

}
