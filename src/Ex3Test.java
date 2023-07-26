import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Ex3Test extends AbstractWebTest {

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
    public void ex3_CancelSearch(){
        waitAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                ERROR_MESSAGE,
                DEFAULT_WAIT_TIME
        );

        waitAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Java",
                ERROR_MESSAGE,
                DEFAULT_WAIT_TIME
        );

        List<WebElement> articles = waitElementsPresent(articlesLocator,
                "No articles found in search list",
                DEFAULT_WAIT_TIME
        );
        //Или можно вместо предыдущего шага использовать getElements + assert ниже
        Assert.assertNotNull("No articles found in search list ",articles);

        waitAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Cannot find X to cancel search",
                DEFAULT_WAIT_TIME
        );

        waitElementsNotPresent(getElements(articlesLocator),
                "Articles are still present in search list",
                3
        );
    }
}
