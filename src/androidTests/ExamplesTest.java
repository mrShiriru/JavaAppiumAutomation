package androidTests;

import lib.CoreTestCase;
import org.junit.jupiter.api.Test;

import static lib.ui.AnyPage.FIRST_ARTICLE;

public class ExamplesTest extends CoreTestCase {


    @Test
    public void testCheckSearchInput(){
        mainPage.waitForFreeEncyclopedia();
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
