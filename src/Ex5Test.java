import lib.CoreTestCase;
import lib.ui.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Ex5Test extends CoreTestCase {

    SearchPage searchPage;
    ArticlePage articlePage;
    MainPage mainPage;
    SavedPage savedPage;
    GroupPage groupPage;

    @Before
    public void loading(){
        searchPage = new SearchPage(driver);
        articlePage = new ArticlePage(driver);
        mainPage = new MainPage(driver);
        savedPage = new SavedPage(driver);
        groupPage = new GroupPage(driver);
        AnyPage anyPAge = new AnyPage(driver);
        anyPAge.skipOnboarding();
    }

    final int FIRST_ARTICLE = 0;
    final int SECOND_ARTICLE = 1;

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
