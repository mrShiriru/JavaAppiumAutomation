package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.ui.panels.BottomPanel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static lib.CoreTestCase.SHORT_WAIT_TIME;

public class MainPage extends AnyPage {

    BottomPanel bottomPanel;

    private static final By SKIP_BUTTON = By.id("org.wikipedia:id/fragment_onboarding_skip_button");

    public MainPage(AppiumDriver<WebElement> driver) {
        super(driver);
        bottomPanel = new BottomPanel(driver);
    }

    public BottomPanel getBottomPanel() {
        return bottomPanel;
    }

    public void skipOnboarding(){
        waitAndClick(
                SKIP_BUTTON,
                "Cannot click Skip button",
                SHORT_WAIT_TIME);
    }
}
