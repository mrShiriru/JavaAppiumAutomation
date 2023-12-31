import lib.CoreTestCase;
import lib.ui.ArticlePage;
import lib.ui.MainPage;
import lib.ui.SearchPage;
import org.junit.Before;
import org.junit.Test;

public class Ex6Test extends CoreTestCase {

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
