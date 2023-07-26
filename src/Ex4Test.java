import lib.CoreTestCase;
import lib.ui.AnyPage;
import lib.ui.SearchPage;
import org.junit.Before;
import org.junit.Test;

public class Ex4Test extends CoreTestCase {

    AnyPage anyPAge;
    SearchPage searchPage;


    @Before
    public void loading(){
        anyPAge = new AnyPage(driver);
        searchPage = new SearchPage(driver);
        anyPAge.skipOnboarding();
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
        String searchValue = "Java";

        searchPage.clickSearchInput();
        searchPage.typeIntoSearchInput(searchValue);
        searchPage.checkArticlesPresentInSearchList();
        searchPage.checkTextInEachSearchResult(searchValue);
    }


}
