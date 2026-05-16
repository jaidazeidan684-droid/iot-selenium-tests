import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SettingsTest extends BaseTest {

    @Test(description = "TC-032 | Settings page loads with Traffic card")
    public void settingsHasTrafficCard() {
        login();
        driver.get(BASE_URL + "/settings");
        WebElement trafficCard = wait.until(org.openqa.selenium.support.ui.ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//app-settings-card[@title='Traffic']")));
        Assert.assertTrue(trafficCard.isDisplayed());
    }

    @Test(description = "TC-033 | Settings page loads with Air Pollution card")
    public void settingsHasAirPollutionCard() {
        login();
        driver.get(BASE_URL + "/settings");
        WebElement airCard = wait.until(org.openqa.selenium.support.ui.ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//app-settings-card[@title='Air Pollution']")));
        Assert.assertTrue(airCard.isDisplayed());
    }

    @Test(description = "TC-034 | Settings page loads with Street Lights card")
    public void settingsHasStreetLightsCard() {
        login();
        driver.get(BASE_URL + "/settings");
        WebElement lightsCard = wait.until(org.openqa.selenium.support.ui.ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//app-settings-card[@title='Street Lights']")));
        Assert.assertTrue(lightsCard.isDisplayed());
    }

    @Test(description = "TC-035 | Settings content area is visible")
    public void settingsContentVisible() {
        login();
        driver.get(BASE_URL + "/settings");
        WebElement content = wait.until(org.openqa.selenium.support.ui.ExpectedConditions
                .visibilityOfElementLocated(By.cssSelector(".settings-content")));
        Assert.assertTrue(content.isDisplayed());
    }

    @Test(description = "TC-036 | Unauthenticated access to settings redirects to login")
    public void unauthenticatedSettingsRedirects() {
        driver.get(BASE_URL + "/settings");
        wait.until(org.openqa.selenium.support.ui.ExpectedConditions.urlContains("/login"));
        Assert.assertTrue(driver.getCurrentUrl().contains("/login"));
    }
}