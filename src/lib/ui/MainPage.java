package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.ui.panels.BottomPanel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static lib.CoreTestCase.SHORT_WAIT_TIME;

public class MainPage extends AnyPage {

    BottomPanel bottomPanel;

    private static final By SKIP_BUTTON = By.id("org.wikipedia:id/fragment_onboarding_skip_button");

    private final String FREE_ENC_ID = "The free encyclopedia";

    public MainPage(AppiumDriver driver) {
        super(driver);
        bottomPanel = new BottomPanel(driver);
    }

    public BottomPanel getBottomPanel() {
        return bottomPanel;
    }


    public void waitForFreeEncyclopedia(){
        this.waitForElementVisibility("id:" + FREE_ENC_ID, "cannot find element");
    }

    protected WebElement waitForElementVisibility(String locator, String errorMessage) {
        By by = getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.withMessage(errorMessage);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    private By getLocatorByString(String locatorWithBy){
        String[] parts = locatorWithBy.split(":");
        String by = parts[0];
        String locator = parts[1];
        switch (by){
            case "xpath":
                return By.xpath(locator);
            case "id":
                return By.id(locator);
            default:
                throw new IllegalArgumentException("by not found " + by);
        }
    }

    public void skipOnboarding(){
        waitAndClick(
                SKIP_BUTTON,
                "Cannot click Skip button",
                SHORT_WAIT_TIME);
    }
}
