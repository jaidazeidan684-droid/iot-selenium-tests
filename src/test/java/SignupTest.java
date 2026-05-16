import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SignupTest extends BaseTest {

    @Test(description = "TC-008 | Signup page loads with all required fields")
    public void signupPageLoads() {
        driver.get(BASE_URL + "/signup");
        Assert.assertTrue(driver.findElement(By.cssSelector("input[formControlName='firstName']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("input[formControlName='lastName']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("input[formControlName='email']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("input[formControlName='password']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("input[formControlName='confirmPassword']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("button.submit-btn")).isDisplayed());
    }

    @Test(description = "TC-009 | Invalid email shows validation error")
    public void invalidEmailShowsError() {
        driver.get(BASE_URL + "/signup");
        driver.findElement(By.cssSelector("input[formControlName='firstName']")).sendKeys("Test");
        driver.findElement(By.cssSelector("input[formControlName='lastName']")).sendKeys("User");
        driver.findElement(By.cssSelector("input[formControlName='email']")).sendKeys("invalidemail");
        driver.findElement(By.cssSelector("input[formControlName='email']")).sendKeys(Keys.TAB);
        WebElement error = wait.until(org.openqa.selenium.support.ui.ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//div[contains(@class,'error') and contains(text(),'valid email')]")));
        Assert.assertTrue(error.isDisplayed());
    }

    @Test(description = "TC-010 | Weak password shows error")
    public void weakPasswordShowsError() {
        driver.get(BASE_URL + "/signup");
        driver.findElement(By.cssSelector("input[formControlName='firstName']")).sendKeys("Test");
        driver.findElement(By.cssSelector("input[formControlName='lastName']")).sendKeys("User");
        driver.findElement(By.cssSelector("input[formControlName='email']")).sendKeys("test@test.com");
        driver.findElement(By.cssSelector("input[formControlName='password']")).sendKeys("123");
        driver.findElement(By.cssSelector("input[formControlName='password']")).sendKeys(Keys.TAB);
        WebElement error = wait.until(org.openqa.selenium.support.ui.ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//div[contains(@class,'error') and contains(text(),'6')]")));
        Assert.assertTrue(error.isDisplayed());
    }

    @Test(description = "TC-011 | Password mismatch shows error")
    public void passwordMismatchShowsError() {
        driver.get(BASE_URL + "/signup");
        driver.findElement(By.cssSelector("input[formControlName='firstName']")).sendKeys("Test");
        driver.findElement(By.cssSelector("input[formControlName='lastName']")).sendKeys("User");
        driver.findElement(By.cssSelector("input[formControlName='email']")).sendKeys("test@test.com");
        driver.findElement(By.cssSelector("input[formControlName='password']")).sendKeys("Password123!");
        driver.findElement(By.cssSelector("input[formControlName='confirmPassword']")).sendKeys("Different123!");
        driver.findElement(By.cssSelector("input[formControlName='confirmPassword']")).sendKeys(Keys.TAB);
        WebElement error = wait.until(org.openqa.selenium.support.ui.ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//div[contains(@class,'error') and contains(text(),'match')]")));
        Assert.assertTrue(error.isDisplayed());
    }

    @Test(description = "TC-012 | Sign In link navigates back to login")
    public void signInLinkNavigatesToLogin() {
        driver.get(BASE_URL + "/signup");
        driver.findElement(By.cssSelector("p.login-link a")).click();
        wait.until(org.openqa.selenium.support.ui.ExpectedConditions.urlContains("/login"));
        Assert.assertTrue(driver.getCurrentUrl().contains("/login"));
    }

    @Test(description = "TC-013 | Unauthenticated user redirects to login from dashboard")
    public void unauthenticatedRedirectsToLogin() {
        driver.get(BASE_URL + "/dashboard");
        wait.until(org.openqa.selenium.support.ui.ExpectedConditions.urlContains("/login"));
        Assert.assertTrue(driver.getCurrentUrl().contains("/login"));
    }
}