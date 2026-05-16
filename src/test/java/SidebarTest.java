import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SidebarTest extends BaseTest {

    @Test(description = "TC-021 | Sidebar is visible on dashboard")
    public void sidebarIsVisible() {
        login();
        WebElement sidebar = wait.until(org.openqa.selenium.support.ui.ExpectedConditions
                .visibilityOfElementLocated(By.cssSelector(".sidebar")));
        Assert.assertTrue(sidebar.isDisplayed());
    }

    @Test(description = "TC-022 | Sidebar shows company name")
    public void sidebarShowsCompanyName() {
        login();
        WebElement companyName = wait.until(org.openqa.selenium.support.ui.ExpectedConditions
                .visibilityOfElementLocated(By.cssSelector(".company-name")));
        Assert.assertEquals(companyName.getText(), "Sentry IoT");
    }

    @Test(description = "TC-023 | Notifications link navigates to notifications page")
    public void notificationsLinkNavigates() {
        login();
        wait.until(org.openqa.selenium.support.ui.ExpectedConditions
                .elementToBeClickable(By.cssSelector("a.nav-item[href='/notifications']"))).click();
        wait.until(org.openqa.selenium.support.ui.ExpectedConditions.urlContains("/notifications"));
        Assert.assertTrue(driver.getCurrentUrl().contains("/notifications"));
    }

    @Test(description = "TC-024 | Settings link navigates to settings page")
    public void settingsLinkNavigates() {
        login();
        wait.until(org.openqa.selenium.support.ui.ExpectedConditions
                .elementToBeClickable(By.cssSelector("a.nav-item[href='/settings']"))).click();
        wait.until(org.openqa.selenium.support.ui.ExpectedConditions.urlContains("/settings"));
        Assert.assertTrue(driver.getCurrentUrl().contains("/settings"));
    }

    @Test(description = "TC-025 | Sidebar shows logged-in user email")
    public void sidebarShowsUserEmail() {
        login();
        WebElement userEmail = wait.until(org.openqa.selenium.support.ui.ExpectedConditions
                .visibilityOfElementLocated(By.cssSelector(".user-email")));
        Assert.assertFalse(userEmail.getText().isEmpty());
        Assert.assertTrue(userEmail.getText().contains("@"));
    }

    @Test(description = "TC-026 | Sidebar shows logged-in user name")
    public void sidebarShowsUserName() {
        login();
        WebElement userName = wait.until(org.openqa.selenium.support.ui.ExpectedConditions
                .visibilityOfElementLocated(By.cssSelector(".user-name")));
        Assert.assertFalse(userName.getText().isEmpty());
    }

    @Test(description = "TC-027 | Logout redirects to login page")
    public void logoutRedirectsToLogin() {
        login();
        wait.until(org.openqa.selenium.support.ui.ExpectedConditions
                .elementToBeClickable(By.cssSelector("a.logout-btn[title='Logout']"))).click();
        wait.until(org.openqa.selenium.support.ui.ExpectedConditions.urlContains("/login"));
        Assert.assertTrue(driver.getCurrentUrl().contains("/login"));
    }
}