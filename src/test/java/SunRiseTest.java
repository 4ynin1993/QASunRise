import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;

/**
 * Created by dmitry on 22.03.14.
 */
public class SunRiseTest {
    private static final String GOOGLE_CHROME = "chrome";
    private static final String MOZILLA_FIREFOX = "firefox";


    private WebDriver driver;

    @Parameters({ "browser", "hub", "url" })
    @BeforeMethod
    public void setUp(@Optional("firefox")String browser, @Optional("http://127.0.0.1:4444/wd/hub")String hub, @Optional("http://go.mail.ru")String url)
            throws MalformedURLException {

        URL host = new URL(hub);
        if (browser.equals(GOOGLE_CHROME)) {
            driver = new RemoteWebDriver(host, DesiredCapabilities.chrome());
        } else if (browser.equals(MOZILLA_FIREFOX)) {
            driver = new RemoteWebDriver(host, DesiredCapabilities.firefox());
        } else {
            throw new IllegalArgumentException();
        }
        driver.manage().window().maximize();
        driver.get(url);
    }

    @AfterMethod
    public void tearDown() {
        driver.close();
    }

    @Test
    public void yearTest() {
        //проверить соответствие отображаемого года и реального
        SunRise sunrise = SunRiseCreator.getInstance().getSunRise(driver);
        final int sunYear = sunrise.getYear();
        final int realYear = Calendar.getInstance().get(Calendar.YEAR);
        Assert.assertEquals(realYear, sunYear);
    }

    @Test
    public void MonthTest() {
        //проверить соответствие отображаемого месяца и реального
        SunRise sunrise = SunRiseCreator.getInstance().getSunRise(driver);
        final int sunCurrentDayOfMonth = sunrise.getMonth();
        final int realCurrentDayOfMonth = Calendar.getInstance().get(Calendar.MONTH);
        Assert.assertEquals(realCurrentDayOfMonth, sunCurrentDayOfMonth);
    }

    @Test
    public void dayOfMonthTest() {
        //проверить соответствие отображаемого дня в месяце и реального
        SunRise sunrise = SunRiseCreator.getInstance().getSunRise(driver);
        final int sunCurrentDayOfMonth = sunrise.getDayOfMonth();
        final int realCurrentDayOfMonth = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        Assert.assertEquals(realCurrentDayOfMonth, sunCurrentDayOfMonth);
    }

    @Test
    public  void sunRiseTest() {
        //проверить соответствие данных о восходе, по умолчанию и при указании реального местоположения(города)
        SunRise sunrise = SunRiseCreator.getInstance().getSunRise(driver);
        String sunrise_rise = sunrise.getRise();
        sunrise.typeLocation();
        String handy_sunrise_rise = sunrise.getRise();
        Assert.assertEquals(sunrise_rise, handy_sunrise_rise);
    }

    @Test
    public  void sunSetTest() {
        //проверить соответствие данных о закате, по умолчанию и при указании реального местоположения(города)
        SunRise sunrise = SunRiseCreator.getInstance().getSunRise(driver);
        String sunrise_set = sunrise.getSet();
        sunrise.typeLocation();
        String handy_sunrise_set = sunrise.getSet();
        Assert.assertEquals(sunrise_set, handy_sunrise_set);
    }

    @Test
    public  void sunRiseTransitTest() {
        //проверить соответствие данных о долготе дня по умолчанию и при указании реального местоположения(города)
        SunRise sunrise = SunRiseCreator.getInstance().getSunRise(driver);
        String sunrise_transmit = sunrise.getTransit();
        sunrise.typeLocation();
        String handy_sunrise_transmit = sunrise.getTransit();
        Assert.assertEquals(sunrise_transmit, handy_sunrise_transmit);
    }

    @Test
    public  void imageCheck() {
        //проверить наличие картинки
        SunRise sunrise = SunRiseCreator.getInstance().getSunRise(driver);
        WebElement sunImage = sunrise.getImage();
        Assert.assertNotNull(sunImage);
    }
}


