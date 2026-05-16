package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SidebarPage extends BasePage {

    private final By sidebar = By.cssSelector(".sidebar");
    private final By companyName = By.cssSelector(".company-name");
    private final By notificationsLink = By.cssSelector("a.nav-item[href='/notifications']");
    private final By settingsLink = By.cssSelector("a.nav-item[href='/settings']");
    private final By userEmail = By.cssSelector(".user-email");
    private final By userName = By.cssSelector(".user-name");
    private final By logoutButton = By.cssSelector("a.logout-btn[title='Logout']");

    public SidebarPage(WebDriver driver) {
        super(driver);
    }

    public boolean isSidebarDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(sidebar)).isDisplayed();
    }

    public String getCompanyName() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(companyName)).getText();
    }

    public void clickNotificationsLink() {
        wait.until(ExpectedConditions.elementToBeClickable(notificationsLink)).click();
    }

    public void clickSettingsLink() {
        wait.until(ExpectedConditions.elementToBeClickable(settingsLink)).click();
    }

    public String getUserEmail() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(userEmail)).getText();
    }

    public String getUserName() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(userName)).getText();
    }

    public void clickLogout() {
        wait.until(ExpectedConditions.elementToBeClickable(logoutButton)).click();
    }
}