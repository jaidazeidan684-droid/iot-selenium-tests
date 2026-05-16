import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class BaseTest {

    protected WebDriver driver;
    protected WebDriverWait wait;

    protected static final String BASE_URL = "http://localhost:4200";
    protected static final String VALID_EMAIL = "testuser@iot.com";
    protected static final String VALID_PASSWORD = "Password123!";

    @BeforeMethod
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @AfterMethod
    public void teardown() {
        if (driver != null) driver.quit();
    }

    protected void login() {
        driver.get(BASE_URL + "/login");
        driver.findElement(org.openqa.selenium.By.cssSelector("input[type='email']")).sendKeys(VALID_EMAIL);
        driver.findElement(org.openqa.selenium.By.cssSelector("input[formControlName='password']")).sendKeys(VALID_PASSWORD);
        driver.findElement(org.openqa.selenium.By.cssSelector("button.login-btn")).click();
        wait.until(org.openqa.selenium.support.ui.ExpectedConditions.urlContains("/dashboard"));
    }
}