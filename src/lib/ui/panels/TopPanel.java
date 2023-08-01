package lib.ui.panels;

import io.appium.java_client.AppiumDriver;
import lib.ui.AnyPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static lib.CoreTestCase.SHORT_WAIT_TIME;

public class TopPanel extends AnyPage {

    public TopPanel(AppiumDriver driver) {
        super(driver);
    }

    public static final By NAVIGATE_UP = By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']");

    public void clickNavigateUpButton(){
        waitAndClick(
                NAVIGATE_UP,
                "Unable to click on the 'Navigate Up' button",
                SHORT_WAIT_TIME
        );
    }


}
