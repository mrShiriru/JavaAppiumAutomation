package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.ui.interfaces.Article;
import lib.ui.panels.TopPanel;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static lib.CoreTestCase.DEFAULT_WAIT_TIME;
import static lib.CoreTestCase.SHORT_WAIT_TIME;

public class SearchPage  extends AnyPage implements Article {

    TopPanel topPanel;

    private static final By
            SEARCH_INPUT_ELEMENT = By.xpath("//*[contains(@text,'Search Wikipedia')]"),
            SEARCH_CLOSE_BUTTON = By.id("org.wikipedia:id/search_close_btn"),
            ARTICLES_IN_SEARCH_LIST = By.xpath(
                    "//*[@resource-id='org.wikipedia:id/search_results_list']//android.view.ViewGroup"),
            PAGE_LIST_ITEM_TITLE = By.xpath(".//*[@resource-id='org.wikipedia:id/page_list_item_title']");

    public static final String
            SEARCH_RESULT_BY_SUBSTRING_TPL = "//*[@resource-id='org.wikipedia:id/page_list_item_description' and " +
                "contains(@text,'{SUBSTRING}')]";

    public SearchPage(AppiumDriver<WebElement> driver) {
        super(driver);
        topPanel = new TopPanel(driver);
    }

    public TopPanel getTopPanel(){
        return topPanel;
    }

    /* TEMPLATES METHODS */
    private static By getResultSearchElement(String substring){
        return By.xpath(SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring));
    }
    /* TEMPLATES METHODS */

    public void clickSearchInput(){
        waitAndClick(SEARCH_INPUT_ELEMENT, "Cannot find and click search input element", SHORT_WAIT_TIME);
    }

    public void typeIntoSearchInput(String text){
        waitAndSendKeys(SEARCH_INPUT_ELEMENT, text, "Cannot find and type into search input", SHORT_WAIT_TIME);
    }

    public void waitForSearchResult(String substring){
        waitElementPresent(getResultSearchElement(substring),"Current article not found", DEFAULT_WAIT_TIME);
    }

    public void waitAndClickSearchCloseButton(){
        waitAndClick(
                SEARCH_CLOSE_BUTTON, "Cannot find and click X to cancel search", SHORT_WAIT_TIME);
    }

    public void waitForCancelButtonToDisappear(){
        waitElementNotPresent(SEARCH_CLOSE_BUTTON, "X is still present on the page", SHORT_WAIT_TIME);
    }

    public void checkArticlesPresentInSearchList() {
        List<WebElement> articles =  waitElementsPresent(
                ARTICLES_IN_SEARCH_LIST,
                "Cannot find articles in the search list",
                DEFAULT_WAIT_TIME);
        Assert.assertNotNull("No articles found in search list ", articles);
    }

    public void waitForArticlesToDisappear(){
        waitElementsNotPresent(
                getElements(ARTICLES_IN_SEARCH_LIST),
                "Articles are still present in search list",
                SHORT_WAIT_TIME);
    }

    public void checkTextInEachSearchResult(String text){
        List<WebElement> articles =  getElements(ARTICLES_IN_SEARCH_LIST);

        for (WebElement article : articles){
            String actualTitle = getTitle(article);

            Assert.assertTrue("Text is not contains in search title",actualTitle.contains(text));
        }
    }

    public WebElement getArticleFromList(int numberOfArticle) {
        List<WebElement> articles =  waitElementsPresent(ARTICLES_IN_SEARCH_LIST,
                "No articles found in the search list",
                DEFAULT_WAIT_TIME);

        return articles.get(numberOfArticle);
    }

    public String getTitle(WebElement article) {
        return article.findElement(PAGE_LIST_ITEM_TITLE).getText();
    }
}
