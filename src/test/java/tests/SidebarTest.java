package tests;

import Pages.SidebarPage;
import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SidebarTest extends BaseTest {

    @Test(description = "TC-021 | Sidebar is visible on dashboard")
    public void sidebarIsVisible() {
        login();
        SidebarPage sidebarPage = new SidebarPage(driver);
        Assert.assertTrue(sidebarPage.isSidebarDisplayed());
    }

    @Test(description = "TC-022 | Sidebar shows company name")
    public void sidebarShowsCompanyName() {
        login();
        SidebarPage sidebarPage = new SidebarPage(driver);
        Assert.assertEquals(sidebarPage.getCompanyName(), "Sentry IoT");
    }

    @Test(description = "TC-023 | Notifications link navigates to notifications page")
    public void notificationsLinkNavigates() {
        login();
        WebElement link = wait.until(ExpectedConditions
                .presenceOfElementLocated(By.cssSelector("a.nav-item[href='/notifications']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", link);
        wait.until(ExpectedConditions.urlContains("/notifications"));
        Assert.assertTrue(driver.getCurrentUrl().contains("/notifications"));
    }

    @Test(description = "TC-024 | Settings link navigates to settings page")
    public void settingsLinkNavigates() {
        login();
        WebElement link = wait.until(ExpectedConditions
                .presenceOfElementLocated(By.cssSelector("a.nav-item[href='/settings']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", link);
        wait.until(ExpectedConditions.urlContains("/settings"));
        Assert.assertTrue(driver.getCurrentUrl().contains("/settings"));
    }

    @Test(description = "TC-025 | Sidebar shows logged-in user email")
    public void sidebarShowsUserEmail() {
        login();
        SidebarPage sidebarPage = new SidebarPage(driver);
        String email = sidebarPage.getUserEmail();
        Assert.assertFalse(email.isEmpty());
        Assert.assertTrue(email.contains("@"));
    }

    @Test(description = "TC-026 | Sidebar shows logged-in user name")
    public void sidebarShowsUserName() {
        login();
        SidebarPage sidebarPage = new SidebarPage(driver);
        Assert.assertFalse(sidebarPage.getUserName().isEmpty());
    }

    @Test(description = "TC-027 | Logout redirects to login page")
    public void logoutRedirectsToLogin() {
        login();
        SidebarPage sidebarPage = new SidebarPage(driver);
        sidebarPage.clickLogout();
        wait.until(ExpectedConditions.urlContains("/login"));
        Assert.assertTrue(driver.getCurrentUrl().contains("/login"));
    }
}