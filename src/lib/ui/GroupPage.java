package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.ui.interfaces.Article;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static lib.CoreTestCase.DEFAULT_WAIT_TIME;
import static lib.CoreTestCase.SHORT_WAIT_TIME;

public class GroupPage extends AnyPage implements Article {

    private static final By ARTICLE_LIST_TITLE = By.xpath(
            "//android.widget.TextView[@resource-id='org.wikipedia:id/page_list_item_title']"),
            POPUP_INFO = By.id("org.wikipedia:id/snackbar_text");

    public GroupPage(AppiumDriver driver) {
        super(driver);
    }

    public List<WebElement> getArticlesList() {
        return  waitElementsPresent(
                ARTICLE_LIST_TITLE,
                "Cannot find articles in group list",
                DEFAULT_WAIT_TIME
        );
    }

    public void deleteArticle(int ArticleNumber) {
        List<WebElement> articleList = getArticlesList();
        swipeElementToLeft(articleList.get(ArticleNumber));

        waitElementsPresent(
                POPUP_INFO,
                "Popup with info not found",
                SHORT_WAIT_TIME
        );
    }

    public WebElement getArticleFromList(int numberOfArticle) {
        List<WebElement> articles = getArticlesList();
        return articles.get(numberOfArticle);
    }

    public String getTitle(WebElement article) {
        return article.getText();
    }
}
