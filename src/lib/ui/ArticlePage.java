package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.ui.panels.BottomPanel;
import lib.ui.panels.TopPanel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static lib.CoreTestCase.DEFAULT_WAIT_TIME;
import static lib.CoreTestCase.SHORT_WAIT_TIME;

public class ArticlePage extends AnyPage {

    TopPanel topPanel;
    BottomPanel bottomPanel;

    private static final By
            FRAGMENT_PAGE_COORDINATOR =  By.id("org.wikipedia:id/fragment_page_coordinator"),
            ARTICLE_TITLE = By.xpath("//android.view.View[@resource-id='pcs-edit-section-title-description']" +
            "/preceding-sibling::android.view.View");
    private static final String TITLE_TPL =  "//android.webkit.WebView[contains(@content-desc,'{TITLE}')]";

    public ArticlePage(AppiumDriver driver) {
        super(driver);
        topPanel = new TopPanel(driver);
        bottomPanel = new BottomPanel(driver);
    }

    public TopPanel getTopPanel(){
        return topPanel;
    }

    /* TEMPLATES METHODS */
    private static By getArticleTitleLocator(String title){
        return By.xpath(TITLE_TPL.replace("{TITLE}", title));
    }
    /* TEMPLATES METHODS */

    public void saveArticleInSavedList(){
        bottomPanel.clickSaveButton();

        waitElementPresent(
                FRAGMENT_PAGE_COORDINATOR,
                "Popup with info not found",
                DEFAULT_WAIT_TIME
        );
    }

    public void checkTitlePresentInArticle(String titleName){

        waitElementPresent(getArticleTitleLocator(titleName),
                String.format("Cannot find title '%s' in the current article", titleName),
                DEFAULT_WAIT_TIME);
    }

    public String getTitle() {
        WebElement actualArticle =  waitElementPresent(
                ARTICLE_TITLE,
                "Article title not found",
                SHORT_WAIT_TIME);

        return actualArticle.getAttribute("name");
    }

    private int getAmountOfElements(By by){
        List<WebElement> elements = driver.findElements(by);
        return elements.size();
    }

    public void assertElementPresent(String title){
        By locator = getArticleTitleLocator(title);

        int amount = getAmountOfElements(getArticleTitleLocator(title));
        if (amount == 0){
            String default_message = "Element "+ locator + " supposed to be present";
            throw new AssertionError(default_message);
        }
    }


}
