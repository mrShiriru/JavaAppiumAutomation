package iosTests;

import lib.CoreTestCase;
import org.junit.jupiter.api.Test;

import static lib.ui.AnyPage.FIRST_ARTICLE;

public class ExamplesTest extends CoreTestCase {

    @Test
    public void testCheckSearchInput(){
        searchPage.clickSearchInput();
        searchPage.typeIntoSearchInput("Java");
        searchPage.waitForSearchResult("Object-oriented programming language");
    }
}
