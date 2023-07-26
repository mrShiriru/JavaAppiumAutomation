import lib.CoreTestCase;
import lib.ui.MainPage;
import lib.ui.SearchPage;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Ex6Test extends CoreTestCase {

    MainPage mainPage;

    @Before
    public void loading(){
        mainPage = new MainPage(driver);
        mainPage.skipOnboarding();
    }

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
    public void testEx6_assertTitle() {
        SearchPage searchPage = new SearchPage(driver);
        String searchValue = "Linkin Park";

        searchPage.clickSearchInput();
        searchPage.typeIntoSearchInput(searchValue);
        List<WebElement> articles = mainPage.waitElementsPresent(searchResultListLocator,
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
