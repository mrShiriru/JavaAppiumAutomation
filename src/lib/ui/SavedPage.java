package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static lib.CoreTestCase.SHORT_WAIT_TIME;

public class SavedPage extends AnyPage {
    public SavedPage(AppiumDriver driver) {
        super(driver);
    }

    private static final By
            SAVED_GROUP = By.xpath("//android.view.ViewGroup" +
            "//android.widget.TextView[@resource-id='org.wikipedia:id/item_title' and @text='Saved']");

    public void clickSavedGroup(){
        waitAndClick(SAVED_GROUP,
                "Can't open Saved group",
                SHORT_WAIT_TIME);
    }
}
