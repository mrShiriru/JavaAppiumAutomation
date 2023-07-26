import lib.CoreTestCase;
import lib.ui.MainPage;
import lib.ui.SearchPage;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Ex4Test extends CoreTestCase {

    MainPage mainPage;

    @Before
    public void loading(){
        mainPage = new MainPage(driver);
        mainPage.skipOnboarding();
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
    public void testEx4_checkWordInSearchResult(){
        SearchPage searchPage = new SearchPage(driver);

        searchPage.clickSearchInput();
        searchPage.typeIntoSearchInput("Java");

        mainPage.waitElementsPresent(articlesLocator,
                "No articles found in search list",
                DEFAULT_WAIT_TIME
        );
        checkTextInEachSearchResult("Java");
    }

    private void checkTextInEachSearchResult(String text){
        By itemTitleLocator = By.xpath(".//*[@resource-id='org.wikipedia:id/page_list_item_title']");

        List<WebElement> articles =  mainPage.getElements(articlesLocator);

        for (WebElement article : articles){
            String actualTitle = article.findElement(itemTitleLocator).getText();

            Assert.assertTrue("Text is not contains in search title",actualTitle.contains(text));
        }
    }

}
