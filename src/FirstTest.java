
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
                By.xpath("//*[contains(@text,'Search…')]"),
                "Java",
                ERROR_MESSAGE,
                DEFAULT_WAIT_TIME
        );

        waitElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']" +
                                "//*[contains(@text,'Object-oriented programming language')]"
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



    /**
     * Необходимо написать функцию, которая проверяет наличие ожидаемого текста у элемента. Предлагается назвать ее
     * assertElementHasText. На вход эта функция должна принимать локатор элемент, ожидаемый текст и текст ошибки,
     * который будет написан в случае, если элемент по этому локатору не содержит текст, который мы ожидаем.
     *
     * Также, необходимо написать тест, который проверяет, что поле ввода для поиска статьи содержит текст (в разных
     * версиях приложения это могут быть тексты "Search..." или "Search Wikipedia", правильным вариантом следует считать
     * тот, которые есть сейчас). Очевидно, что тест должен использовать написанный ранее метод.
     *
     * Результат выполнения задания нужно отдельным коммитом положить в git. В качестве ответа прислать ссылку на
     * коммит. Если вам потребовалось несколько коммитов для выполнения одного задания - присылайте ссылки на все эти
     * коммиты с комментариями.
     */
    public void assertElementHasText(By locator, String expectedText, String errorMessage){
        WebElement element = waitElementPresent(locator,ERROR_MESSAGE,DEFAULT_WAIT_TIME);
        String actualText = element.getAttribute("text");

        Assert.assertEquals(errorMessage, expectedText, actualText);
    }

    @Test
    public void ex2_CreateMethod (){
        By searchLocator = By.xpath("//*[@resource-id='org.wikipedia:id/search_container']//android.widget.TextView");
        assertElementHasText(searchLocator, "Search Wikipedia", "Expected text not found");
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

    private final By articlesLocator = By.xpath(
            "//*[@resource-id='org.wikipedia:id/search_results_list']//android.widget.LinearLayout");

    private final By itemTitleLocator = By.xpath(".//*[@resource-id='org.wikipedia:id/page_list_item_title']");

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


    /**
     * Ex4*: Тест: проверка слов в поиске
     * Написать тест, который делает поиск по какому-то слову. Например, JAVA. Затем убеждается, что в каждом результате
     * поиска есть это слово.
     *
     * Внимание, прокручивать результаты выдачи поиска не надо. Это мы научимся делать на следующих занятиях.
     * Пока надо работать только с теми результатами поиска, который видны сразу, без прокрутки.
     */
    @Test
    public void ex4_checkWordInSearchResult(){
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
        waitElementsPresent(articlesLocator,
                "No articles found in search list",
                DEFAULT_WAIT_TIME
        );
        checkTextInEachSearchResult("Java");
    }

    private void checkTextInEachSearchResult(String text){
        List<WebElement> articles = getElements(articlesLocator);

        for (WebElement article : articles){
            String actualTitle = article.findElement(itemTitleLocator).getText();

            Assert.assertTrue("Text is not contains in search title",actualTitle.contains(text));
        }
    }

}
