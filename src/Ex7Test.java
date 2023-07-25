import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.ScreenOrientation;

public class Ex7Test extends AbstractWebTest {


    /**
     * Один из вариантов решения проблемы, чтобы экран всегда оказывался в правильном положении, это
     * Пример 1:
     * Создать after метод в тех тестах, где происходит разворот экрана и вернуть его в изначальное состояние
     *
     * Пример 2:
     * создать еще один Before метод и указать какую ориентацию экрана необходимо для этого теста
     */

    @Before
    public void orientation2() throws Exception
    {
        checkOrientation(ScreenOrientation.LANDSCAPE);
    }

    @After
    public void orientation1() throws Exception
    {
        checkOrientation(ScreenOrientation.LANDSCAPE);
    }

    @Test
    public void ex7_OrientationScreen_1() {

        checkOrientation(ScreenOrientation.PORTRAIT);

        driver.rotate(ScreenOrientation.LANDSCAPE);

        checkOrientation(ScreenOrientation.PORTRAIT);

        boolean check_after = isCurrentOrientationPortrait();
    }

    private void checkOrientation(ScreenOrientation orientation){
        String ort = driver.getOrientation().name();
        ScreenOrientation actualOrientation = ScreenOrientation.valueOf(ort);

        if (orientation != actualOrientation){
            driver.rotate(orientation);
        }
    }

    private boolean isCurrentOrientationPortrait(){
        String ort = driver.getOrientation().name();
        ScreenOrientation orientation = ScreenOrientation.valueOf(ort);

        if (orientation == ScreenOrientation.PORTRAIT) {
            System.out.println("Current screen orientation is portrait");
            return true;

        } else {
            System.out.println("Current screen orientation is Landscape");
            return false;
        }
    }


}
