package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SignupPage extends BasePage {

    private final By firstNameInput = By.cssSelector("input[formControlName='firstName']");
    private final By lastNameInput = By.cssSelector("input[formControlName='lastName']");
    private final By emailInput = By.cssSelector("input[formControlName='email']");
    private final By passwordInput = By.cssSelector("input[formControlName='password']");
    private final By confirmPasswordInput = By.cssSelector("input[formControlName='confirmPassword']");
    private final By submitButton = By.cssSelector("button.submit-btn");
    private final By signInLink = By.cssSelector("p.login-link a");

    public SignupPage(WebDriver driver) {
        super(driver);
    }

    public void navigateTo(String baseUrl) {
        driver.get(baseUrl + "/signup");
    }

    public void enterFirstName(String name) {
        driver.findElement(firstNameInput).sendKeys(name);
    }

    public void enterLastName(String name) {
        driver.findElement(lastNameInput).sendKeys(name);
    }

    public void enterEmail(String email) {
        driver.findElement(emailInput).sendKeys(email);
        driver.findElement(emailInput).sendKeys(Keys.TAB);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordInput).sendKeys(password);
        driver.findElement(passwordInput).sendKeys(Keys.TAB);
    }

    public void enterConfirmPassword(String password) {
        driver.findElement(confirmPasswordInput).sendKeys(password);
        driver.findElement(confirmPasswordInput).sendKeys(Keys.TAB);
    }

    public void clickSignInLink() {
        driver.findElement(signInLink).click();
    }

    public boolean isFirstNameDisplayed() {
        return driver.findElement(firstNameInput).isDisplayed();
    }

    public boolean isLastNameDisplayed() {
        return driver.findElement(lastNameInput).isDisplayed();
    }

    public boolean isEmailDisplayed() {
        return driver.findElement(emailInput).isDisplayed();
    }

    public boolean isPasswordDisplayed() {
        return driver.findElement(passwordInput).isDisplayed();
    }

    public boolean isConfirmPasswordDisplayed() {
        return driver.findElement(confirmPasswordInput).isDisplayed();
    }

    public boolean isSubmitButtonDisplayed() {
        return driver.findElement(submitButton).isDisplayed();
    }

    public WebElement getValidationError(String containsText) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(@class,'error') and contains(text(),'" + containsText + "')]")));
    }
}