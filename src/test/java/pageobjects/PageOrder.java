package pageobjects;
import org.openqa.selenium.*;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

public class PageOrder {
    private final WebDriver driver;
    private static final By CookieBannerButton = By.className("App_CookieButton__3cvqF");
    private static final By OrderButtonHeader = By.xpath("//*[@id='root']/div/div[1]/div[1]/div[2]/button[1]");
    private static final By OrderButtonDown = By.xpath("//*[@id='root']/div/div/div[4]/div[2]/div[5]/button");
    private static final By NameButton = By.xpath("//*[@id='root']/div/div[2]/div[2]/div[1]/input");
    private static final By SurnameButton = By.xpath("//*[@id='root']/div/div[2]/div[2]/div[2]/input");
    private static final By AddressButton = By.xpath("//*[@id='root']/div/div[2]/div[2]/div[3]/input");
    private static final By MetroButton = By.xpath("//*[@id='root']/div/div[2]/div[2]/div[4]/div/div/input");
    private static final By TelButton = By.xpath("//*[@id='root']/div/div[2]/div[2]/div[5]/input");
    private static final By NextButton = By.xpath("//*[@id='root']/div/div[2]/div[3]/button");
    private static final By DateButton = By.xpath("//*[@id='root']/div/div[2]/div[2]/div[1]/div/div/input");
    private static final By PeriodButton = By.xpath("//*[@id='root']/div/div[2]/div[2]/div[2]/div/div");
    private static final By BlackButton = By.xpath("//*[@id='root']/div/div[2]/div[2]/div[3]/label[1]/input");
    private static final By GreyButton = By.xpath("//*[@id='grey']");
    private static final By CommentButton = By.xpath("//*[@id='root']/div/div[2]/div[2]/div[4]/input");
    private static final By SubmitButton = By.xpath("//*[@id='root']/div/div[2]/div[3]/button[2]");
    private static final By PopapButton = By.xpath("//*[@id='root']/div/div[2]/div[5]/div[1]");
    private static final By YesButton = By.xpath("//*[@id='root']/div/div[2]/div[5]/div[2]/button[2]");

    public PageOrder(WebDriver driver) {
        this.driver = driver;
    }
    public void closeCookieBanner() {
        driver.findElement(CookieBannerButton).click();
    }
    public void scrollPageOrder() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();",
                driver.findElement(OrderButtonDown));
    }
    public void clickOrderButtonDown() {
        driver.findElement(OrderButtonDown).click();
    }
    public void clickOrderButtonHeader() {
        driver.findElement(OrderButtonHeader).click();
    }
    public void enterDataFirstPageOrder(String name, String surname, String address, int metro, String phoneNumber) {
        driver.findElement(NameButton).sendKeys(name);
        driver.findElement(SurnameButton).sendKeys(surname);
        driver.findElement(AddressButton).sendKeys(address);
        driver.findElement(MetroButton).click();
        By allMetroStation = By.xpath("//*[@id='root']/div/div[2]/div[2]/div[4]/div/div[2]/ul/li");
        List<WebElement> elements = driver.findElements(allMetroStation);
        elements.get(metro - 1).click();
        driver.findElement(TelButton).sendKeys(phoneNumber);
        driver.findElement(NextButton).click();
    }
    public void enterDataSecondPageOrder(String color, String date, int rentalDays, String comment) {
        if ("черный".equals(color)) {
            driver.findElement(BlackButton).click();
        } else if ("серый".equals(color)) {
            driver.findElement(GreyButton).click();
        }
        driver.findElement(DateButton).sendKeys(date);
        driver.findElement(DateButton).sendKeys(Keys.RETURN);
        driver.findElement(PeriodButton).click();
        By allDayForOrder = By.className("Dropdown-option");
        List<WebElement> elements = driver.findElements(allDayForOrder);
        elements.get(rentalDays - 1).click();
        driver.findElement(CommentButton).sendKeys(comment);
        driver.findElement(SubmitButton).click();
        driver.findElement(YesButton).click();
    }
    public boolean successfullyText() {
        String successfullOrder = driver.findElement(PopapButton).getText();
        return successfullOrder.contains("Заказ оформлен");
    }
}