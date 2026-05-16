package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {

    private final By emailInput = By.cssSelector("input[type='email']");
    private final By passwordInput = By.cssSelector("input[formControlName='password']");
    private final By loginButton = By.cssSelector("button.login-btn");
    private final By errorMessage = By.cssSelector(".login-error");
    private final By eyeButton = By.cssSelector("button.eye-btn");
    private final By signUpLink = By.cssSelector("p.switch-text a");
    private final By footerText = By.cssSelector("p.footer-text");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void navigateTo(String baseUrl) {
        driver.get(baseUrl + "/login");
    }

    public void enterEmail(String email) {
        driver.findElement(emailInput).sendKeys(email);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordInput).sendKeys(password);
    }

    public void clickLogin() {
        driver.findElement(loginButton).click();
    }

    public void clickEyeButton() {
        driver.findElement(eyeButton).click();
    }

    public void clickSignUpLink() {
        driver.findElement(signUpLink).click();
    }

    public boolean isEmailInputDisplayed() {
        return driver.findElement(emailInput).isDisplayed();
    }

    public boolean isPasswordInputDisplayed() {
        return driver.findElement(passwordInput).isDisplayed();
    }

    public boolean isLoginButtonDisplayed() {
        return driver.findElement(loginButton).isDisplayed();
    }

    public String getPasswordInputType() {
        return driver.findElement(passwordInput).getAttribute("type");
    }

    public WebElement getErrorMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
    }

    public String getFooterText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(footerText)).getText();
    }

    public void login(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickLogin();
    }
}