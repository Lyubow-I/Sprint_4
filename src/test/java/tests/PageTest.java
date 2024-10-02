package tests;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pageobjects.PageOrder;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.TimeUnit;
import static org.junit.Assert.assertTrue;
@RunWith(Parameterized.class)
public class PageTest {

    private WebDriver driver;
    private PageOrder pageOrder;

    @Parameterized.Parameter(0)
    public String name;
    @Parameterized.Parameter(1)
    public String surname;
    @Parameterized.Parameter(2)
    public String address;
    @Parameterized.Parameter(3)
    public int metro;
    @Parameterized.Parameter(4)
    public String phoneNumber;
    @Parameterized.Parameter(5)
    public String color;
    @Parameterized.Parameter(6)
    public String date;
    @Parameterized.Parameter(7)
    public int rentalDays;
    @Parameterized.Parameter(8)
    public String comment;

    @Before
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.get("https://qa-scooter.praktikum-services.ru/");
        pageOrder = new PageOrder(driver);
        pageOrder.closeCookieBanner();
    }
    @Test
    public void pageTestThroughHeaderButton() {
        pageOrder.clickOrderButtonHeader();
        pageOrder.enterDataFirstPageOrder(name, surname, address, metro, phoneNumber);
        pageOrder.enterDataSecondPageOrder(color, date, rentalDays, comment);
        assertTrue(pageOrder.successfullyText());
    }
    @Test
    public void pageTestThroughDownButton() {
        pageOrder.scrollPageOrder();
        pageOrder.clickOrderButtonDown();
        pageOrder.enterDataFirstPageOrder(name, surname, address, metro, phoneNumber);
        pageOrder.enterDataSecondPageOrder(color, date, rentalDays, comment);
        assertTrue(pageOrder.successfullyText());
    }
    @After
    public void teardown() {
        driver.quit();
    }
    @Parameterized.Parameters
    public static Collection<Object[]> testData() {
        return Arrays.asList(new Object[][]{
                {"Любовь", "Сидорова", "Ленина", 5, "+79667653344", "черный", "1.11.2024", 5, "Стучите"},
                {"Никита", "Иванов", "Чкалова", 14, "+79541112233", "серый", "2.11.2024", 6, ""},
        });
    }
}

