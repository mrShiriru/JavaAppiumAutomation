import lib.CoreTestCase;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.ScreenOrientation;


public class Ex7Test extends CoreTestCase {

    /**
     * Один из вариантов решения проблемы, чтобы экран всегда оказывался в правильном положении, это
     * Добавить настройку при старте эмулятора, например вот так:
     *      DesiredCapabilities capabilities = new DesiredCapabilities();
     *      capabilities.setCapability("deviceOrientation", "portrait");
     */

    @Test
    public void testEx7_OrientationScreen() {
        boolean check_before = isCurrentOrientationPortrait();
        driver.rotate(ScreenOrientation.LANDSCAPE);
        boolean check_after = isCurrentOrientationPortrait();

        Assert.assertNotEquals("Screen orientation after rotation is the same", check_before, check_after);
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
