package tests;

import Pages.LoginPage;
import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test(description = "TC-001 | Login page loads with correct elements")
    public void loginPageLoads() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateTo(BASE_URL);
        Assert.assertTrue(loginPage.isEmailInputDisplayed());
        Assert.assertTrue(loginPage.isPasswordInputDisplayed());
        Assert.assertTrue(loginPage.isLoginButtonDisplayed());
    }

    @Test(description = "TC-002 | Valid login redirects to dashboard")
    public void validLoginRedirectsToDashboard() {
        login();
        Assert.assertTrue(driver.getCurrentUrl().contains("/dashboard"));
    }

    @Test(description = "TC-003 | Wrong password shows error message")
    public void wrongPasswordShowsError() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateTo(BASE_URL);
        loginPage.login(VALID_EMAIL, "WrongPass999!");
        Assert.assertTrue(loginPage.getErrorMessage().isDisplayed());
    }

    @Test(description = "TC-004 | Empty form stays on login page")
    public void emptyFormStaysOnLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateTo(BASE_URL);
        loginPage.clickLogin();
        Assert.assertTrue(driver.getCurrentUrl().contains("/login"));
    }

    @Test(description = "TC-005 | Password toggle shows and hides password")
    public void passwordToggleWorks() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateTo(BASE_URL);
        loginPage.enterPassword("mySecret");
        Assert.assertEquals(loginPage.getPasswordInputType(), "password");
        loginPage.clickEyeButton();
        Assert.assertEquals(loginPage.getPasswordInputType(), "text");
    }

    @Test(description = "TC-006 | Sign Up link navigates to signup page")
    public void signUpLinkNavigatesToSignup() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateTo(BASE_URL);
        loginPage.clickSignUpLink();
        wait.until(org.openqa.selenium.support.ui.ExpectedConditions.urlContains("/signup"));
        Assert.assertTrue(driver.getCurrentUrl().contains("/signup"));
    }

    @Test(description = "TC-007 | Footer text is displayed")
    public void footerTextIsDisplayed() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateTo(BASE_URL);
        Assert.assertEquals(loginPage.getFooterText(), "Authorized Personnel Only");
    }
}