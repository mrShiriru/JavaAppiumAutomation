package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static lib.CoreTestCase.DEFAULT_WAIT_TIME;
import static lib.CoreTestCase.SHORT_WAIT_TIME;

public class SearchPage  extends  MainPage{


    public static final By
            SEARCH_INPUT_ELEMENT = By.xpath("//*[contains(@text,'Search Wikipedia')]"),
            SEARCH_CLOSE_BUTTON = By.id("org.wikipedia:id/search_close_btn");

    public static final String
            SEARCH_RESULT_BY_SUBSTRING_TPL = "//*[@resource-id='org.wikipedia:id/page_list_item_description' and " +
                "contains(@text,'{SUBSTRING}')]";

    public SearchPage(AppiumDriver<WebElement> driver) {
        super(driver);
    }

    /* TEMPLATES METHODS */
    private static By getResultSearchElement(String substring){
        return By.xpath(SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring));
    }
    /* TEMPLATES METHODS */

    public void clickSearchInput(){
        waitAndClick(SEARCH_INPUT_ELEMENT, "Cannot find and click search input element", SHORT_WAIT_TIME);
    }

    public void typeIntoSearchInput(String text){
        waitAndSendKeys(SEARCH_INPUT_ELEMENT, text, "Cannot find and type into search input", SHORT_WAIT_TIME);
    }

    public void waitForSearchResult(String substring){
        waitElementPresent(getResultSearchElement(substring),"Current article not found", DEFAULT_WAIT_TIME);
    }

    public void waitAndClickSearchCloseButton(){
        waitAndClick(
                SEARCH_CLOSE_BUTTON, "Cannot find and click X to cancel search", SHORT_WAIT_TIME);
    }

    public void waitForCancelButtonToDisappear(){
        waitElementNotPresent(SEARCH_CLOSE_BUTTON, "X is still present on the page", SHORT_WAIT_TIME);
    }


}
