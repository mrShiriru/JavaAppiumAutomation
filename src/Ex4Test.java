import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Ex4Test extends AbstractWebTest{

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
        By itemTitleLocator = By.xpath(".//*[@resource-id='org.wikipedia:id/page_list_item_title']");

        List<WebElement> articles = getElements(articlesLocator);

        for (WebElement article : articles){
            String actualTitle = article.findElement(itemTitleLocator).getText();

            Assert.assertTrue("Text is not contains in search title",actualTitle.contains(text));
        }
    }

}
