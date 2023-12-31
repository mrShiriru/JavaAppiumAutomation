import lib.CoreTestCase;
import lib.ui.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import java.util.List;

import static lib.ui.AnyPage.FIRST_ARTICLE;
import static lib.ui.AnyPage.SECOND_ARTICLE;

public class Ex5Test extends CoreTestCase {

    /**
     * Написать тест, который:
     *
     * 1. Сохраняет две статьи в одну папку
     * 2. Удаляет одну из статей
     * 3. Убеждается, что вторая осталась
     * 4. Переходит в неё и убеждается, что title совпадает
     */
    @Test
    public void testEx5_testCreateTwoArticle() {
        String searchValue = "Appium";

        searchPage.clickSearchInput();
        searchPage.typeIntoSearchInput(searchValue);
        saveCurrentArticle(FIRST_ARTICLE);
        saveCurrentArticle(SECOND_ARTICLE);
        searchPage.getTopPanel().clickNavigateUpButton();
        mainPage.getBottomPanel().clickSavedButton();
        savedPage.clickSavedGroup();

        List<WebElement> items = groupPage.getArticlesList();
        groupPage.deleteArticle(FIRST_ARTICLE);
        List<WebElement> items2 =  groupPage.getArticlesList();
        Assert.assertTrue("The first article hasn't been removed",items.size() > items2.size());

        String expectedTitle = groupPage.saveTitleAndOpenArticle(FIRST_ARTICLE);

        String actualTitle = articlePage.getTitle();
        Assert.assertEquals(ERROR_MESSAGE,expectedTitle,actualTitle);
    }

    private void saveCurrentArticle(int articleNumber){
        String title = searchPage.saveTitleAndOpenArticle(articleNumber);
        articlePage.checkTitlePresentInArticle(title);
        articlePage.saveArticleInSavedList();
        articlePage.getTopPanel().clickNavigateUpButton();
    }
}
