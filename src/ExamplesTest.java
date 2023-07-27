
import lib.CoreTestCase;
import lib.ui.ArticlePage;
import lib.ui.MainPage;
import lib.ui.SearchPage;
import org.junit.Before;
import org.junit.Test;

import static lib.ui.AnyPage.FIRST_ARTICLE;

public class ExamplesTest extends CoreTestCase {

    MainPage mainPage;
    SearchPage searchPage;
    ArticlePage articlePage;

    @Before
    public void loading(){
        searchPage = new SearchPage(driver);
        articlePage = new ArticlePage(driver);
        mainPage = new MainPage(driver);
        mainPage.skipOnboarding();
    }

    @Test
    public void testCheckSearchInput(){
        searchPage.clickSearchInput();
        searchPage.typeIntoSearchInput("Java");
        searchPage.waitForSearchResult("Object-oriented programming language");
    }

    @Test
    public void testCancelSearch(){
        searchPage.clickSearchInput();
        searchPage.typeIntoSearchInput("Java");
        searchPage.waitAndClickSearchCloseButton();
        searchPage.waitForCancelButtonToDisappear();
    }

    @Test
    public void testSwipeUp(){
        searchPage.clickSearchInput();
        searchPage.typeIntoSearchInput("Java");
        searchPage.saveTitleAndOpenArticle(FIRST_ARTICLE);
        articlePage.swipeUp();
        articlePage.swipeUp();
        articlePage.swipeUp();
    }
}
