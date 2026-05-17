package tests;

import Pages.SignupPage;
import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SignupTest extends BaseTest {

    @Test(description = "TC-008 | Signup page loads with all required fields")
    public void signupPageLoads() {
        SignupPage signupPage = new SignupPage(driver);
        signupPage.navigateTo(BASE_URL);
        Assert.assertTrue(signupPage.isFirstNameDisplayed());
        Assert.assertTrue(signupPage.isLastNameDisplayed());
        Assert.assertTrue(signupPage.isEmailDisplayed());
        Assert.assertTrue(signupPage.isPasswordDisplayed());
        Assert.assertTrue(signupPage.isConfirmPasswordDisplayed());
        Assert.assertTrue(signupPage.isSubmitButtonDisplayed());
    }

    @Test(description = "TC-009 | Invalid email shows validation error")
    public void invalidEmailShowsError() {
        SignupPage signupPage = new SignupPage(driver);
        signupPage.navigateTo(BASE_URL);
        signupPage.enterFirstName("Test");
        signupPage.enterLastName("User");
        signupPage.enterEmail("invalidemail");
        Assert.assertTrue(signupPage.getValidationError("valid email").isDisplayed());
    }

    @Test(description = "TC-010 | Weak password shows error")
    public void weakPasswordShowsError() {
        SignupPage signupPage = new SignupPage(driver);
        signupPage.navigateTo(BASE_URL);
        signupPage.enterFirstName("Test");
        signupPage.enterLastName("User");
        signupPage.enterEmail("test@test.com");
        signupPage.enterPassword("123");
        Assert.assertTrue(signupPage.getValidationError("6").isDisplayed());
    }

    @Test(description = "TC-011 | Password mismatch shows error")
    public void passwordMismatchShowsError() {
        SignupPage signupPage = new SignupPage(driver);
        signupPage.navigateTo(BASE_URL);
        signupPage.enterFirstName("Test");
        signupPage.enterLastName("User");
        signupPage.enterEmail("test@test.com");
        signupPage.enterPassword("Password123!");
        signupPage.enterConfirmPassword("Different123!");
        Assert.assertTrue(signupPage.getValidationError("match").isDisplayed());
    }

    @Test(description = "TC-012 | Sign In link navigates back to login")
    public void signInLinkNavigatesToLogin() {
        SignupPage signupPage = new SignupPage(driver);
        signupPage.navigateTo(BASE_URL);
        signupPage.clickSignInLink();
        wait.until(ExpectedConditions.urlContains("/login"));
        Assert.assertTrue(driver.getCurrentUrl().contains("/login"));
    }

    @Test(description = "TC-013 | Unauthenticated user redirects to login from dashboard")
    public void unauthenticatedRedirectsToLogin() {
        driver.get(BASE_URL + "/dashboard");
        wait.until(ExpectedConditions.urlContains("/login"));
        Assert.assertTrue(driver.getCurrentUrl().contains("/login"));
    }

    @Test(description = "TC-037 | Profile picture upload button is displayed on signup page")
    public void profilePictureUploadButtonDisplayed() {
        driver.get(BASE_URL + "/signup");
        WebElement uploadBtn = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.cssSelector("label.upload-btn")));
        Assert.assertTrue(uploadBtn.isDisplayed());
        Assert.assertEquals(uploadBtn.getText().trim(), "Choose File");
    }

    @Test(description = "TC-038 | Empty first name shows required error on signup")
    public void emptyFirstNameShowsError() {
        driver.get(BASE_URL + "/signup");
        WebElement firstName = driver.findElement(By.cssSelector("input[formControlName='firstName']"));
        firstName.click();
        driver.findElement(By.cssSelector("input[formControlName='lastName']")).click();
        WebElement error = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//div[contains(@class,'error') and contains(text(),'Required')]")));
        Assert.assertTrue(error.isDisplayed());
    }

    @Test(description = "TC-039 | Empty last name shows required error on signup")
    public void emptyLastNameShowsError() {
        driver.get(BASE_URL + "/signup");
        WebElement lastName = driver.findElement(By.cssSelector("input[formControlName='lastName']"));
        lastName.click();
        driver.findElement(By.cssSelector("input[formControlName='firstName']")).click();
        lastName.click();
        driver.findElement(By.cssSelector("input[formControlName='email']")).click();
        WebElement error = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//div[contains(@class,'error') and contains(text(),'Required')]")));
        Assert.assertTrue(error.isDisplayed());
    }

    @Test(description = "TC-040 | Confirm password toggle shows and hides password on signup")
    public void confirmPasswordToggleWorks() {
        driver.get(BASE_URL + "/signup");
        WebElement confirmInput = driver.findElement(By.cssSelector("input[formControlName='confirmPassword']"));
        confirmInput.sendKeys("mySecret");
        Assert.assertEquals(confirmInput.getAttribute("type"), "password");
        // Find the second eye button (confirm password toggle)
        java.util.List<WebElement> eyeBtns = driver.findElements(By.cssSelector("button.eye-btn"));
        eyeBtns.get(eyeBtns.size() - 1).click();
        Assert.assertEquals(confirmInput.getAttribute("type"), "text");
    }

    @Test(description = "TC-041 | Password toggle works on signup page")
    public void passwordToggleWorksOnSignup() {
        driver.get(BASE_URL + "/signup");
        WebElement passwordInput = driver.findElement(By.cssSelector("input[formControlName='password']"));
        passwordInput.sendKeys("mySecret");
        Assert.assertEquals(passwordInput.getAttribute("type"), "password");
        java.util.List<WebElement> eyeBtns = driver.findElements(By.cssSelector("button.eye-btn"));
        eyeBtns.get(0).click();
        Assert.assertEquals(passwordInput.getAttribute("type"), "text");
    }
}