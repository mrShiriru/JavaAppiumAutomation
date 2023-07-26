import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Ex6Test extends AbstractWebTest {

    private final By searchInputLocator =  By.xpath("//*[contains(@text,'Search Wikipedia')]");

    private final By searchResultListLocator = By.xpath(
            "//androidx.recyclerview.widget.RecyclerView[@resource-id ='org.wikipedia:id/search_results_list']" +
            "//android.view.ViewGroup"
    );

    /**
     * Написать тест, который открывает статью и убеждается, что у нее есть элемент title.
     * Важно: тест не должен дожидаться появления title, проверка должна производиться сразу.
     * Если title не найден - тест падает с ошибкой. Метод можно назвать assertElementPresent.
     */
    @Test
    public void ex6_Ex6_assertTitle() {

        String searchValue = "Linkin Park";

        waitAndClick(
                searchInputLocator,
                "Unable to click on the search input",
                SHORT_WAIT_TIME
        );

        waitAndSendKeys(
                searchInputLocator,
                searchValue,
                String.format("Unable to enter text '%s' on search input", searchValue),
                SHORT_WAIT_TIME
        );

        List<WebElement> articles = waitElementsPresent(searchResultListLocator,
                "No articles found in the search list",
                DEFAULT_WAIT_TIME
        );

        WebElement article = articles.get(0);
        article.findElement(By.xpath(
                ".//android.widget.TextView[@resource-id ='org.wikipedia:id/page_list_item_title']")
        ).getText();

        article.click();

        assertElementPresent(
                By.xpath("//android.view.View[@resource-id='pcs-edit-section-title-description']" +
                "/preceding-sibling::android.view.View")
        );
    }

    private int getAmountOfElements(By by){
        List<WebElement> elements = driver.findElements(by);
        return elements.size();
    }

    private void assertElementPresent(By locator){

        int amount = getAmountOfElements(locator);

        if (amount == 0){
            String default_message = "An element "+ locator.toString() + " supposed to be present";
            throw new AssertionError(default_message);
        }
    }

}
