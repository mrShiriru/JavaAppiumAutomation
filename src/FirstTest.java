
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class FirstTest extends AbstractWebTest{

    @Test
    public void testCheckSearchInput(){
        waitAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                ERROR_MESSAGE,
                DEFAULT_WAIT_TIME
        );

        waitAndSendKeys(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Java",
                ERROR_MESSAGE,
                DEFAULT_WAIT_TIME
        );

        waitElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_description' and " +
                        "contains(@text,'Object-oriented programming language')]"
                ),
                "Current article not found",
                DEFAULT_WAIT_TIME);
    }

    @Test
    public void testCancelSearch(){
        waitAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find search input",
                DEFAULT_WAIT_TIME
        );

        waitAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Cannot find X to cancel search",
                DEFAULT_WAIT_TIME
        );

        waitElementNotPresent(
                By.id("org.wikipedia:id/search_close_btn"),
                "X is still present on the page",
                5
        );
    }

}
