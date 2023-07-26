import lib.CoreTestCase;
import lib.ui.AnyPage;
import lib.ui.ArticlePage;
import lib.ui.SearchPage;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Ex6Test extends CoreTestCase {

    private AnyPage anyPage;
    private SearchPage searchPage;
    private ArticlePage articlePage;

    @Before
    public void loading(){
        anyPage = new AnyPage(driver);
        searchPage = new SearchPage(driver);
        articlePage = new ArticlePage(driver);
        anyPage.skipOnboarding();
    }

    /**
     * Написать тест, который открывает статью и убеждается, что у нее есть элемент title.
     * Важно: тест не должен дожидаться появления title, проверка должна производиться сразу.
     * Если title не найден - тест падает с ошибкой. Метод можно назвать assertElementPresent.
     */
    @Test
    public void testEx6_assertTitle() {
        String searchValue = "Linkin Park";

        searchPage.clickSearchInput();
        searchPage.typeIntoSearchInput(searchValue);

        String title = searchPage.saveTitleAndOpenArticle(0);
        articlePage.assertElementPresent(title);
    }
}
