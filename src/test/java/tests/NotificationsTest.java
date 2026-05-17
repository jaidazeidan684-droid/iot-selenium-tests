package tests;

import Pages.NotificationsPage;
import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;

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
        wait.until(ExpectedConditions.urlContains("/login"));
        Assert.assertTrue(driver.getCurrentUrl().contains("/login"));
    }

    @Test(description = "TC-051 | Notifications page subtitle text is correct")
    public void notificationsSubtitleText() {
        login();
        driver.get(BASE_URL + "/notifications");
        WebElement subtitle = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.cssSelector("p.notif-subtitle")));
        Assert.assertEquals(subtitle.getText(), "Recent threshold crossings and system events.");
    }

    @Test(description = "TC-052 | Mark all as read button is displayed when notifications exist")
    public void markAllAsReadButtonDisplayedWhenNotificationsExist() {
        login();
        driver.get(BASE_URL + "/notifications");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".notif-content")));
        List<WebElement> notifList = driver.findElements(By.cssSelector(".notif-list"));
        List<WebElement> markAllBtn = driver.findElements(By.cssSelector("button.btn-action"));
        if (notifList.size() > 0) {
            Assert.assertTrue(markAllBtn.size() > 0);
            Assert.assertTrue(markAllBtn.get(0).isDisplayed());
        } else {
            Assert.assertTrue(driver.findElement(By.cssSelector(".notif-empty")).isDisplayed());
        }
    }

    @Test(description = "TC-053 | Mark all as read button clicks without error")
    public void markAllAsReadButtonClickable() {
        login();
        driver.get(BASE_URL + "/notifications");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".notif-content")));
        List<WebElement> markAllBtn = driver.findElements(By.cssSelector("button.btn-action"));
        if (markAllBtn.size() > 0) {
            markAllBtn.get(0).click();
            Assert.assertTrue(driver.getCurrentUrl().contains("/notifications"));
        } else {
            Assert.assertTrue(driver.findElement(By.cssSelector(".notif-empty")).isDisplayed());
        }
    }
}