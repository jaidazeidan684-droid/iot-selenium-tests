package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SettingsPage extends BasePage {

    private final By trafficCard = By.xpath("//app-settings-card[@title='Traffic']");
    private final By airPollutionCard = By.xpath("//app-settings-card[@title='Air Pollution']");
    private final By streetLightsCard = By.xpath("//app-settings-card[@title='Street Lights']");
    private final By settingsContent = By.cssSelector(".settings-content");

    public SettingsPage(WebDriver driver) {
        super(driver);
    }

    public void navigateTo(String baseUrl) {
        driver.get(baseUrl + "/settings");
    }

    public boolean isTrafficCardDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(trafficCard)).isDisplayed();
    }

    public boolean isAirPollutionCardDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(airPollutionCard)).isDisplayed();
    }

    public boolean isStreetLightsCardDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(streetLightsCard)).isDisplayed();
    }

    public boolean isSettingsContentDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(settingsContent)).isDisplayed();
    }
}