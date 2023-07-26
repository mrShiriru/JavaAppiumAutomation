
import lib.CoreTestCase;
import lib.ui.MainPage;
import lib.ui.SearchPage;
import org.junit.Before;
import org.junit.Test;

public class FirstTest extends CoreTestCase {

    MainPage mainPage;
    SearchPage searchPage;

    @Before
    public void loading(){
        mainPage = new MainPage(driver);
        searchPage = new SearchPage(driver);
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
        searchPage.waitAndClickSearchCloseButton();
        searchPage.waitForCancelButtonToDisappear();
    }

}
