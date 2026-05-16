package tests;

import Pages.SignupPage;
import base.BaseTest;
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