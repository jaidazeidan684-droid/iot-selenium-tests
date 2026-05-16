import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test(description = "TC-001 | Login page loads with correct elements")
    public void loginPageLoads() {
        driver.get(BASE_URL + "/login");
        Assert.assertTrue(driver.findElement(By.cssSelector("input[type='email']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("input[formControlName='password']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("button.login-btn")).isDisplayed());
    }

    @Test(description = "TC-002 | Valid login redirects to dashboard")
    public void validLoginRedirectsToDashboard() {
        login();
        Assert.assertTrue(driver.getCurrentUrl().contains("/dashboard"));
    }

    @Test(description = "TC-003 | Wrong password shows error message")
    public void wrongPasswordShowsError() {
        driver.get(BASE_URL + "/login");
        driver.findElement(By.cssSelector("input[type='email']")).sendKeys(VALID_EMAIL);
        driver.findElement(By.cssSelector("input[formControlName='password']")).sendKeys("WrongPass999!");
        driver.findElement(By.cssSelector("button.login-btn")).click();
        WebElement error = wait.until(org.openqa.selenium.support.ui.ExpectedConditions
                .visibilityOfElementLocated(By.cssSelector(".login-error")));
        Assert.assertTrue(error.isDisplayed());
    }

    @Test(description = "TC-004 | Empty form stays on login page")
    public void emptyFormStaysOnLogin() {
        driver.get(BASE_URL + "/login");
        driver.findElement(By.cssSelector("button.login-btn")).click();
        Assert.assertTrue(driver.getCurrentUrl().contains("/login"));
    }

    @Test(description = "TC-005 | Password toggle shows and hides password")
    public void passwordToggleWorks() {
        driver.get(BASE_URL + "/login");
        WebElement passInput = driver.findElement(By.cssSelector("input[formControlName='password']"));
        passInput.sendKeys("mySecret");
        Assert.assertEquals(passInput.getAttribute("type"), "password");
        driver.findElement(By.cssSelector("button.eye-btn")).click();
        Assert.assertEquals(passInput.getAttribute("type"), "text");
    }

    @Test(description = "TC-006 | Sign Up link navigates to signup page")
    public void signUpLinkNavigatesToSignup() {
        driver.get(BASE_URL + "/login");
        driver.findElement(By.cssSelector("p.switch-text a")).click();
        wait.until(org.openqa.selenium.support.ui.ExpectedConditions.urlContains("/signup"));
        Assert.assertTrue(driver.getCurrentUrl().contains("/signup"));
    }

    @Test(description = "TC-007 | Footer text is displayed")
    public void footerTextIsDisplayed() {
        driver.get(BASE_URL + "/login");
        WebElement footer = wait.until(org.openqa.selenium.support.ui.ExpectedConditions
                .visibilityOfElementLocated(By.cssSelector("p.footer-text")));
        Assert.assertEquals(footer.getText(), "Authorized Personnel Only");
    }
}