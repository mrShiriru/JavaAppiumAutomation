package lib.ui.interfaces;

import org.openqa.selenium.WebElement;

public interface Article {

    default String saveTitleAndOpenArticle(int numberOfArticle) {
        WebElement article = getArticleFromList(numberOfArticle);
        String expectedTitle = getTitle(article);
        article.click();
        return expectedTitle;
    }

    WebElement getArticleFromList(int numberOfArticle);
    String getTitle(WebElement article);


}
