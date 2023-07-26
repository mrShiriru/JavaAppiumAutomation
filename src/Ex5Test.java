import lib.CoreTestCase;
import lib.ui.MainPage;
import lib.ui.SearchPage;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Ex5Test extends CoreTestCase {

    MainPage mainPage;

    @Before
    public void loading(){
        mainPage = new MainPage(driver);
        mainPage.skipOnboarding();
    }


    private final By searchResultListLocator = By.xpath(
            "//androidx.recyclerview.widget.RecyclerView[@resource-id ='org.wikipedia:id/search_results_list']" +
            "//android.view.ViewGroup"
    );

    By pageListItemTitleLocator = By.xpath(
            "//android.widget.TextView[@resource-id='org.wikipedia:id/page_list_item_title']"
    );

    By saveButtonArticleNavTabLocator = By.xpath(
            "//android.widget.TextView[@content-desc='Save' and @resource-id='org.wikipedia:id/page_save']"
    );

    By  saveButtonNavigationTabLocator = By.xpath("//android.widget.FrameLayout[@content-desc='Saved']");

    By navigateUpLocator = By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']");

    By savedGroupLocator = By.xpath("//android.view.ViewGroup" +
            "//android.widget.TextView[@resource-id='org.wikipedia:id/item_title' and @text='Saved']");


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
        SearchPage searchPage = new SearchPage(driver);

        searchPage.clickSearchInput();
        searchPage.typeIntoSearchInput(searchValue);

        saveCurrentArticle(FIRST_ARTICLE);
        saveCurrentArticle(SECOND_ARTICLE);

        mainPage.waitAndClick(navigateUpLocator,"Unable to click on the 'Navigate Up' button", SHORT_WAIT_TIME);

        mainPage.waitAndClick(saveButtonNavigationTabLocator,
                "Unable to click on the 'Save' button in the navigation tab",
                SHORT_WAIT_TIME);

        mainPage.waitAndClick(savedGroupLocator,
                ERROR_MESSAGE,
                SHORT_WAIT_TIME);

        List<WebElement> items =  mainPage.waitElementsPresent(
                pageListItemTitleLocator,
                ERROR_MESSAGE,
                DEFAULT_WAIT_TIME
        );

        mainPage.swipeElementToLeft(items.get(FIRST_ARTICLE));

        mainPage.waitElementsPresent(
                By.id("org.wikipedia:id/snackbar_text"),
                "Popup with info not found",
                SHORT_WAIT_TIME
        );

        List<WebElement> items2 =  mainPage.waitElementsPresent(
                pageListItemTitleLocator,
                ERROR_MESSAGE,
                DEFAULT_WAIT_TIME
        );
        Assert.assertTrue("The first article hasn't been removed",items.size() > items2.size());

        WebElement lastArticle = items2.get(FIRST_ARTICLE);
        String expectedTitle = lastArticle.getText();
        lastArticle.click();


        WebElement actualArticle =  mainPage.waitElementPresent(
                By.xpath("//android.view.View[@resource-id='pcs-edit-section-title-description']" +
                        "/preceding-sibling::android.view.View"),
                "Article not found",
                DEFAULT_WAIT_TIME);

        String actualTitle = actualArticle.getAttribute("name");

        Assert.assertEquals(ERROR_MESSAGE,expectedTitle,actualTitle);
    }

    private void saveCurrentArticle(int ArticleNumber){
        List<WebElement> articles =  mainPage.waitElementsPresent(searchResultListLocator,
                "No articles found in the search list",
                DEFAULT_WAIT_TIME
        );

        WebElement article = articles.get(ArticleNumber);

        String text = article.findElement(By.xpath(
                ".//android.widget.TextView[@resource-id ='org.wikipedia:id/page_list_item_title']")
        ).getText();

        article.click();

        mainPage.waitElementPresent(
                By.xpath("//android.webkit.WebView[contains(@content-desc,'"+text+"')]"),
                ERROR_MESSAGE,
                DEFAULT_WAIT_TIME);

        mainPage.waitAndClick(
                saveButtonArticleNavTabLocator,
                "Unable to click on the 'Save' button in the bottom menu",
                SHORT_WAIT_TIME
        );

        mainPage.waitElementPresent(
        By.id("org.wikipedia:id/fragment_page_coordinator"),
                "Popup with info not found",
                DEFAULT_WAIT_TIME
        );

        mainPage.waitAndClick(
                navigateUpLocator,
                "Unable to click on the 'Navigate Up' button",
                SHORT_WAIT_TIME
        );
    }
}
