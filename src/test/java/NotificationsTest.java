import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;

public class NotificationsTest extends BaseTest {

    @Test(description = "TC-028 | Notifications page loads with correct title")
    public void notificationsPageTitle() {
        login();
        driver.get(BASE_URL + "/notifications");
        WebElement title = wait.until(org.openqa.selenium.support.ui.ExpectedConditions
                .visibilityOfElementLocated(By.cssSelector("h1.notif-title")));
        Assert.assertEquals(title.getText(), "Notifications");
    }

    @Test(description = "TC-029 | Notifications page shows subtitle")
    public void notificationsPageSubtitle() {
        login();
        driver.get(BASE_URL + "/notifications");
        WebElement subtitle = wait.until(org.openqa.selenium.support.ui.ExpectedConditions
                .visibilityOfElementLocated(By.cssSelector("p.notif-subtitle")));
        Assert.assertTrue(subtitle.isDisplayed());
    }

    @Test(description = "TC-030 | Notifications shows list or empty state")
    public void notificationsShowsContent() {
        login();
        driver.get(BASE_URL + "/notifications");
        wait.until(org.openqa.selenium.support.ui.ExpectedConditions
                .visibilityOfElementLocated(By.cssSelector(".notif-content")));
        List<WebElement> emptyMsg = driver.findElements(By.cssSelector(".notif-empty"));
        List<WebElement> notifList = driver.findElements(By.cssSelector(".notif-list"));
        Assert.assertTrue(emptyMsg.size() > 0 || notifList.size() > 0);
    }

    @Test(description = "TC-031 | Unauthenticated access to notifications redirects to login")
    public void unauthenticatedNotificationsRedirects() {
        driver.get(BASE_URL + "/notifications");
        wait.until(org.openqa.selenium.support.ui.ExpectedConditions.urlContains("/login"));
        Assert.assertTrue(driver.getCurrentUrl().contains("/login"));
    }
}