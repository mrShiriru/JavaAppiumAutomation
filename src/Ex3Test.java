import lib.CoreTestCase;
import lib.ui.MainPage;
import lib.ui.SearchPage;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Ex3Test extends CoreTestCase {

    MainPage mainPage;

    @Before
    public void loading(){
        mainPage = new MainPage(driver);
        mainPage.skipOnboarding();
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
        SearchPage searchPage = new SearchPage(driver);

        searchPage.clickSearchInput();
        searchPage.typeIntoSearchInput("Java");

        List<WebElement> articles =  mainPage.waitElementsPresent(articlesLocator,
                "No articles found in search list",
                DEFAULT_WAIT_TIME
        );
        //Или можно вместо предыдущего шага использовать getElements + assert ниже
        Assert.assertNotNull("No articles found in search list ",articles);

        mainPage.waitAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Cannot find X to cancel search",
                DEFAULT_WAIT_TIME
        );

        mainPage.waitElementsNotPresent( mainPage.getElements(articlesLocator),
                "Articles are still present in search list",
                3
        );
    }
}
