import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Ex5Test extends AbstractWebTest {

    private final By searchInputLocator =  By.xpath("//*[contains(@text,'Search Wikipedia')]");

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

    @Test
    public void ex5_testCreateTwoArticle() {

        String searchValue = "Appium";

        waitAndClick(
                searchInputLocator,
                "Unable to click on the search input",
                SHORT_WAIT_TIME
        );

        waitAndSendKeys(
                searchInputLocator,
                searchValue,
                String.format("Unable to enter text '%s' on search input", searchValue),
                SHORT_WAIT_TIME
        );

        saveCurrentArticle(FIRST_ARTICLE);
        saveCurrentArticle(SECOND_ARTICLE);

        waitAndClick(navigateUpLocator,"Unable to click on the 'Navigate Up' button", SHORT_WAIT_TIME);

        waitAndClick(saveButtonNavigationTabLocator,
                "Unable to click on the 'Save' button in the navigation tab",
                SHORT_WAIT_TIME);

        waitAndClick(savedGroupLocator,
                ERROR_MESSAGE,
                SHORT_WAIT_TIME);

        List<WebElement> items = waitElementsPresent(
                pageListItemTitleLocator,
                ERROR_MESSAGE,
                DEFAULT_WAIT_TIME
        );

        swipeElementToLeft(items.get(FIRST_ARTICLE));

        waitElementsPresent(
                By.id("org.wikipedia:id/snackbar_text"),
                "Popup with info not found",
                SHORT_WAIT_TIME
        );

        List<WebElement> items2 = waitElementsPresent(
                pageListItemTitleLocator,
                ERROR_MESSAGE,
                DEFAULT_WAIT_TIME
        );
        Assert.assertTrue("The first article hasn't been removed",items.size() > items2.size());

        WebElement lastArticle = items2.get(FIRST_ARTICLE);
        String expectedTitle = lastArticle.getText();
        lastArticle.click();


        WebElement actualArticle = waitElementPresent(
                By.xpath("//android.view.View[@resource-id='pcs-edit-section-title-description']" +
                        "/preceding-sibling::android.view.View"),
                "Article not found",
                DEFAULT_WAIT_TIME);

        String actualTitle = actualArticle.getAttribute("name");;


        Assert.assertEquals(ERROR_MESSAGE,expectedTitle,actualTitle);

    }


    private void saveCurrentArticle(int ArticleNumber){

        List<WebElement> articles = waitElementsPresent(searchResultListLocator,
                "No articles found in the search list",
                DEFAULT_WAIT_TIME
        );

        WebElement article = articles.get(ArticleNumber);

        String text = article.findElement(By.xpath(
                ".//android.widget.TextView[@resource-id ='org.wikipedia:id/page_list_item_title']")
        ).getText();

        article.click();

        waitElementPresent(
                By.xpath("//android.webkit.WebView[contains(@content-desc,'"+text+"')]"),
                ERROR_MESSAGE,
                DEFAULT_WAIT_TIME);

        waitAndClick(
                saveButtonArticleNavTabLocator,
                "Unable to click on the 'Save' button in the bottom menu",
                SHORT_WAIT_TIME
        );

        waitElementPresent(
        By.id("org.wikipedia:id/fragment_page_coordinator"),
                "Popup with info not found",
                DEFAULT_WAIT_TIME
        );


        waitAndClick(
                navigateUpLocator,
                "Unable to click on the 'Navigate Up' button",
                SHORT_WAIT_TIME
        );

    }

}
