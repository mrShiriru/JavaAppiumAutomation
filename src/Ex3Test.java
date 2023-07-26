import lib.CoreTestCase;
import lib.ui.AnyPage;
import lib.ui.SearchPage;
import org.junit.Before;
import org.junit.Test;

public class Ex3Test extends CoreTestCase {

    AnyPage anyPAge;
    SearchPage searchPage;

    @Before
    public void loading(){
        anyPAge = new AnyPage(driver);
        searchPage = new SearchPage(driver);
        anyPAge.skipOnboarding();
    }

    /**
     * Написать тест, который:
     *
     * Ищет какое-то слово
     * Убеждается, что найдено несколько статей
     * Отменяет поиск
     * Убеждается, что результат поиска пропал
     *
     * Результат выполнения закоммитить в репозиторий на гитхаб и прислать ссылку на коммит. Если вам потребовалось
     * несколько коммитов для выполнения одного задания - присылайте ссылки на все эти коммиты с комментариями.
     */
    @Test
    public void testEx3_CancelSearch(){
        searchPage.clickSearchInput();
        searchPage.typeIntoSearchInput("Java");
        searchPage.checkArticlesPresentInSearchList();
        searchPage.waitAndClickSearchCloseButton();
        searchPage.waitForArticlesToDisappear();
    }
}
