package tests;

import Pages.NotificationsPage;
import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NotificationsTest extends BaseTest {

    @Test(description = "TC-028 | Notifications page loads with correct title")
    public void notificationsPageTitle() {
        login();
        NotificationsPage notificationsPage = new NotificationsPage(driver);
        notificationsPage.navigateTo(BASE_URL);
        Assert.assertEquals(notificationsPage.getPageTitle(), "Notifications");
    }

    @Test(description = "TC-029 | Notifications page shows subtitle")
    public void notificationsPageSubtitle() {
        login();
        NotificationsPage notificationsPage = new NotificationsPage(driver);
        notificationsPage.navigateTo(BASE_URL);
        Assert.assertTrue(notificationsPage.isSubtitleDisplayed());
    }

    @Test(description = "TC-030 | Notifications shows list or empty state")
    public void notificationsShowsContent() {
        login();
        NotificationsPage notificationsPage = new NotificationsPage(driver);
        notificationsPage.navigateTo(BASE_URL);
        Assert.assertTrue(notificationsPage.isContentDisplayed());
        Assert.assertTrue(notificationsPage.hasEmptyStateOrList());
    }

    @Test(description = "TC-031 | Unauthenticated access to notifications redirects to login")
    public void unauthenticatedNotificationsRedirects() {
        driver.get(BASE_URL + "/notifications");
        wait.until(org.openqa.selenium.support.ui.ExpectedConditions.urlContains("/login"));
        Assert.assertTrue(driver.getCurrentUrl().contains("/login"));
    }
}