
import lib.CoreTestCase;
import lib.ui.AnyPage;
import lib.ui.SearchPage;
import org.junit.Before;
import org.junit.Test;

public class FirstTest extends CoreTestCase {

    AnyPage anyPAge;
    SearchPage searchPage;

    @Before
    public void loading(){
        anyPAge = new AnyPage(driver);
        searchPage = new SearchPage(driver);
        anyPAge.skipOnboarding();
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

}
