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

    private final By
            NEXT_BUTTON = By.xpath("//XCUIElementTypeStaticText[@name='Next']"),
            SKIP_BUTTON = By.xpath("//XCUIElementTypeButton[@name='Skip']"),
            FREE_ENC = By.id("The free encyclopedia");

    public MainPage(AppiumDriver driver) {
        super(driver);
        bottomPanel = new BottomPanel(driver);
    }

    public BottomPanel getBottomPanel() {
        return bottomPanel;
    }


    public void waitForFreeEncyclopedia(){
        waitElementVisibility(FREE_ENC,"cannot find element 'The free encyclopedia'");
    }

    public void clickNextButton(){
        waitAndClick(NEXT_BUTTON, "Cannot click Next button on page", SHORT_WAIT_TIME);
    }

    public void skipOnboarding(){
        waitForFreeEncyclopedia();
        waitAndClick(
                SKIP_BUTTON,
                "Cannot click Skip button",
                SHORT_WAIT_TIME);
    }

    public void agreeOnboarding(){
        waitForFreeEncyclopedia();
        clickNextButton();
    }
}
