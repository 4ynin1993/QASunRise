import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by dmitry on 23.03.14.
 */
public class SunRise {
    public static final String LOCATION= "Москва";
    public static final String INPUT_ID= "geoName";
    private final WebDriver driver;
    private static final String[] Months = {
        "января", "февраля", "марта", "апреля", "мая", "июня", "июля",
        "августа", "сентября", "октября", "ноября", "декабря" };

    public SunRise(WebDriver driver) {
        this.driver = driver;
    }

    public int getDayOfMonth() {
        return (Integer.valueOf(parseDate('d')));
    }

    public int getMonth() {
        WebElement Month = driver.findElement(By.id("calSelect"));
        return parseDate('m');
    }

    public int getYear() {
        return (Integer.valueOf(parseDate('y')));
    }
    private int parseDate(char typeOfDate) {
        WebElement date = driver.findElement(By.id("calSelect"));
        String currentDate = date.getText();
        String[] s = currentDate.split(" ");
        switch(typeOfDate) {
            case 'd':
                return Integer.valueOf(s[0]);
            case 'm':
                int i=0;
                while (!Months[i].equals(s[1])){
                    i++;
                }
                return i;
            case 'y':
                s[2] = s[2].substring(0,4);
                return Integer.valueOf(s[2]);
            default:
                return -1;

        }

    }
    public void typeLocation() {
        WebElement input = driver.findElement(By.id(INPUT_ID));
        input.sendKeys(LOCATION);
        WebElement autoCompleteButton = driver.findElement(By.className("autocomplete_item"));
        autoCompleteButton.click();
    }
}
