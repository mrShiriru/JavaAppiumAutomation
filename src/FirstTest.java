
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.List;

public class FirstTest {

    private AppiumDriver<WebElement> driver;
    public static final long DEFAULT_WAIT_TIME = 15;
    public static final String ERROR_MESSAGE = "Element not found";

    @Before
    public void setUp() throws Exception
    {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("deviceName","AndroidTestDevice");
        capabilities.setCapability("platformVersion","8.1.0");
        capabilities.setCapability("automationName","Appium");
        capabilities.setCapability("appPackage","org.wikipedia");
        capabilities.setCapability("appActivity",".main.MainActivity");
        capabilities.setCapability("app","C:\\Users\\KGrigorchuk\\Desktop\\mobile app automator\\JavaAppiumAutomation\\JavaAppiumAutomation\\apks\\org.wikipedia.apk");

        driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

    }

    @After
    public void tearDown(){
        driver.quit();
    }

    @Test
    public void firstTest(){
        waitAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                ERROR_MESSAGE,
                DEFAULT_WAIT_TIME
        );

        waitAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Java",
                ERROR_MESSAGE,
                DEFAULT_WAIT_TIME
        );

        waitElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']" +
                                "//*[contains(@text,'Object-oriented programming language')]"
                ),
                "Current article not found",
                DEFAULT_WAIT_TIME);
    }

    @Test
    public void testCancelSearch(){
        waitAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find search input",
                DEFAULT_WAIT_TIME
        );

        waitAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Cannot find X to cancel search",
                DEFAULT_WAIT_TIME
        );

        waitElementNotPresent(
                By.id("org.wikipedia:id/search_close_btn"),
                "X is still present on the page",
                5
        );
    }

    private WebElement waitElementPresent(By locator, String errorMsg, long timeoutInSeconds){
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(errorMsg + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    private boolean waitElementNotPresent(By locator, String errorMsg, long timeoutInSeconds){
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(errorMsg + "\n");
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    private WebElement waitAndClick(By locator, String errorMsg, long timeoutInSeconds){
        WebElement element = waitElementPresent(locator, errorMsg, timeoutInSeconds);
        element.click();
        return element;
    }

    private WebElement waitAndSendKeys(By by, String value, String errorMsg, long timeoutInSeconds){
        WebElement element = waitElementPresent(by, errorMsg, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    /**
     * Необходимо написать функцию, которая проверяет наличие ожидаемого текста у элемента. Предлагается назвать ее
     * assertElementHasText. На вход эта функция должна принимать локатор элемент, ожидаемый текст и текст ошибки,
     * который будет написан в случае, если элемент по этому локатору не содержит текст, который мы ожидаем.
     *
     * Также, необходимо написать тест, который проверяет, что поле ввода для поиска статьи содержит текст (в разных
     * версиях приложения это могут быть тексты "Search..." или "Search Wikipedia", правильным вариантом следует считать
     * тот, которые есть сейчас). Очевидно, что тест должен использовать написанный ранее метод.
     *
     * Результат выполнения задания нужно отдельным коммитом положить в git. В качестве ответа прислать ссылку на
     * коммит. Если вам потребовалось несколько коммитов для выполнения одного задания - присылайте ссылки на все эти
     * коммиты с комментариями.
     */
    public void assertElementHasText(By locator, String expectedText, String errorMessage){
        WebElement element = waitElementPresent(locator,ERROR_MESSAGE,DEFAULT_WAIT_TIME);
        String actualText = element.getAttribute("text");

        Assert.assertEquals(errorMessage, expectedText, actualText);
    }

    @Test
    public void ex2_CreateMethod (){
        By searchLocator = By.xpath("//*[@resource-id='org.wikipedia:id/search_container']//android.widget.TextView");
        assertElementHasText(searchLocator, "Search Wikipedia", "Expected text not found");
    }


    /**
     * Написать тест, который:
     *
     * Ищет какое-то слово
     * Убеждается, что найдено несколько статей
     * Отменяет поиск
     * Убеждается, что результат поиска пропал
     *
     * Результат выполнения закоммитить в репозиторий на гитхаб и прислать ссылку на коммит. Если вам потребовалось
     * несколько коммитов для выполнения одного задания - присылайте ссылки на все эти коммиты с комментариями.
     */

    private By articlesLocator = By.xpath(
            "//*[@resource-id='org.wikipedia:id/search_results_list']//android.widget.LinearLayout");

    @Test
    public void ex3_CancelSearch(){
        waitAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                ERROR_MESSAGE,
                DEFAULT_WAIT_TIME
        );

        waitAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Java",
                ERROR_MESSAGE,
                DEFAULT_WAIT_TIME
        );

        List<WebElement> articles = waitElementsPresent(articlesLocator,
                "No articles found in search list",
                DEFAULT_WAIT_TIME
        );
        //Или можно вместо предыдущего шага использовать getElements + assert ниже
        Assert.assertNotNull("No articles found in search list ",articles);

        waitAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Cannot find X to cancel search",
                DEFAULT_WAIT_TIME
        );

        waitElementsNotPresent(getElements(articlesLocator),
                "Articles are still present in search list",
                3
        );
    }

    private List<WebElement> getElements(By locator) {
        return driver.findElements(articlesLocator);
    }


    private List<WebElement> waitElementsPresent(By locator, String errorMsg, long timeoutInSeconds){
        return new WebDriverWait(driver, timeoutInSeconds)
                .withMessage(errorMsg + "\n")
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }

    private boolean waitElementsNotPresent(List<WebElement> elements, String errorMsg, long timeoutInSeconds){
        return new WebDriverWait(driver, timeoutInSeconds)
                .withMessage(errorMsg + "\n")
                .until(ExpectedConditions.invisibilityOfAllElements(elements));
    }

}
