package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;

public class NotificationsPage extends BasePage {

    private final By pageTitle = By.cssSelector("h1.notif-title");
    private final By pageSubtitle = By.cssSelector("p.notif-subtitle");
    private final By notifContent = By.cssSelector(".notif-content");
    private final By notifEmpty = By.cssSelector(".notif-empty");
    private final By notifList = By.cssSelector(".notif-list");

    public NotificationsPage(WebDriver driver) {
        super(driver);
    }

    public void navigateTo(String baseUrl) {
        driver.get(baseUrl + "/notifications");
    }

    public String getPageTitle() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(pageTitle)).getText();
    }

    public boolean isSubtitleDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(pageSubtitle)).isDisplayed();
    }

    public boolean isContentDisplayed() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(notifContent));
        return true;
    }

    public boolean hasEmptyStateOrList() {
        List<WebElement> empty = driver.findElements(notifEmpty);
        List<WebElement> list = driver.findElements(notifList);
        return empty.size() > 0 || list.size() > 0;
    }
}