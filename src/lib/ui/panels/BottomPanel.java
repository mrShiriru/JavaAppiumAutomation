package lib.ui.panels;

import io.appium.java_client.AppiumDriver;
import lib.ui.AnyPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static lib.CoreTestCase.SHORT_WAIT_TIME;

public class BottomPanel extends AnyPage {

    public BottomPanel(AppiumDriver driver) {
        super(driver);
    }

    public static final By
            SAVE_BUTTON_ARTICLE_NAV_TAB = By.xpath(
            "//android.widget.TextView[@content-desc='Save' and @resource-id='org.wikipedia:id/page_save']"),
            SAVED_BUTTON_NAVIGATION_TAB = By.xpath("//android.widget.FrameLayout[@content-desc='Saved']");

    public void clickSaveButton(){
        waitAndClick(
                SAVE_BUTTON_ARTICLE_NAV_TAB,
                "Unable to click on the 'Save' button in the bottom menu",
                SHORT_WAIT_TIME
        );
    }

    public void clickSavedButton(){
        waitAndClick(SAVED_BUTTON_NAVIGATION_TAB,
            "Unable to click on the 'Saved' button in the navigation tab",
                SHORT_WAIT_TIME);
    }
}
