import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by dmitry on 23.03.14.
 */

public class SunRiseCreator {

    private static final String SEARCH_VIEW_ID = "q";
    private static final String FIND_BUTTON = "go-form__submit";
    private static final String SEARCH_WORDS = "восход закат";

    private static SunRiseCreator creator;

    private SunRiseCreator() {
    }

    public static SunRiseCreator getInstance() {
        if (creator == null) {
            creator = new SunRiseCreator();
        }

        return creator;
    }
    public SunRise getSunRise(WebDriver driver) {
        goToSunRise(driver);
        return new SunRise(driver);
    }
    private void goToSunRise(WebDriver driver) {
        WebElement searchView = driver.findElement(By.id(SEARCH_VIEW_ID));
        searchView.sendKeys(SEARCH_WORDS);
        WebElement findButton = driver.findElement(By.className(FIND_BUTTON));
        findButton.click();
    }
}