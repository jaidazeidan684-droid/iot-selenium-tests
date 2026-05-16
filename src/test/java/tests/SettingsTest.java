package tests;

import Pages.SettingsPage;
import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SettingsTest extends BaseTest {

    @Test(description = "TC-032 | Settings page loads with Traffic card")
    public void settingsHasTrafficCard() {
        login();
        SettingsPage settingsPage = new SettingsPage(driver);
        settingsPage.navigateTo(BASE_URL);
        Assert.assertTrue(settingsPage.isTrafficCardDisplayed());
    }

    @Test(description = "TC-033 | Settings page loads with Air Pollution card")
    public void settingsHasAirPollutionCard() {
        login();
        SettingsPage settingsPage = new SettingsPage(driver);
        settingsPage.navigateTo(BASE_URL);
        Assert.assertTrue(settingsPage.isAirPollutionCardDisplayed());
    }

    @Test(description = "TC-034 | Settings page loads with Street Lights card")
    public void settingsHasStreetLightsCard() {
        login();
        SettingsPage settingsPage = new SettingsPage(driver);
        settingsPage.navigateTo(BASE_URL);
        Assert.assertTrue(settingsPage.isStreetLightsCardDisplayed());
    }

    @Test(description = "TC-035 | Settings content area is visible")
    public void settingsContentVisible() {
        login();
        SettingsPage settingsPage = new SettingsPage(driver);
        settingsPage.navigateTo(BASE_URL);
        Assert.assertTrue(settingsPage.isSettingsContentDisplayed());
    }

    @Test(description = "TC-036 | Unauthenticated access to settings redirects to login")
    public void unauthenticatedSettingsRedirects() {
        driver.get(BASE_URL + "/settings");
        wait.until(org.openqa.selenium.support.ui.ExpectedConditions.urlContains("/login"));
        Assert.assertTrue(driver.getCurrentUrl().contains("/login"));
    }
}