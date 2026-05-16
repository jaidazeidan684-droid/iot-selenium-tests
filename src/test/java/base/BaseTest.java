package base;

import Pages.LoginPage;
import utils.ExcelUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class BaseTest {

    protected WebDriver driver;
    protected WebDriverWait wait;

    protected static final String BASE_URL = "http://localhost:4200";
    protected static final String VALID_EMAIL = ExcelUtils.getEmail();
    protected static final String VALID_PASSWORD = ExcelUtils.getPassword();

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
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateTo(BASE_URL);
        loginPage.login(VALID_EMAIL, VALID_PASSWORD);
        wait.until(ExpectedConditions.urlContains("/dashboard"));
    }
}