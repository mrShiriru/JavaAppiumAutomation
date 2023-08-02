package androidTests;

import lib.CoreTestCase;
import org.junit.jupiter.api.Test;

public class Ex9Test extends CoreTestCase {

    /**
     * 1. Подобрать локатор, который находит результат поиска одновременно по заголовку и описанию
     * (если заголовок или описание отличается - элемент не находится).
     *
     * 2. Добавить соответствующий метод в секцию TEMPLATES METHODS класса SearchPageObject.
     *
     * 3. В этот же класс добавить метод waitForElementByTitleAndDescription(String title, String description).
     * Он должен дожидаться результата поиска по двум строкам - по заголовку и описанию. Если такой элемент не появляется,
     * тест должен упасть с читаемой и понятной ошибкой.
     *
     * 4. Написать тест, который будет делать поиск по любому запросу на ваш выбор (поиск по этому слову должен возвращать
     * как минимум 3 результата). Далее тест должен убеждаться, что первых три результата присутствуют в результате поиска.
     *
     * Результатом выполнения задания должен быть дифф к коду, который был написан на четвертом занятии.
     * В этом диффе должны быть вспомогательные методы, лежащие в соответствующих классах и код теста,
     * лежащего в соответствующем классе. Тест должен работать (т.е. проходить при верном результате поиска и
     * обязательно падать, если результат поиска изменился).
     */

    @Test
    public void testEx9_workWithTemplates(){
        String title = "Apple";
        String description = "Fruit that grows on a tree";

        String title2 = "Apple Inc.";
        String description2 = "American multinational technology corporation";

        String title3 = "Apple Watch";
        String description3 = "Line of smartwatches designed by Apple Inc.";

        searchPage.clickSearchInput();
        searchPage.typeIntoSearchInput(title);
        searchPage.waitForSearchResult(title,description);
        searchPage.waitForSearchResult(title2,description2);
        searchPage.waitForSearchResult(title3,description3);
    }


}
