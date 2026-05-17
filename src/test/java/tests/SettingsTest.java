package tests;

import Pages.SettingsPage;
import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
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
        wait.until(ExpectedConditions.urlContains("/login"));
        Assert.assertTrue(driver.getCurrentUrl().contains("/login"));
    }

    @Test(description = "TC-042 | Frequency input is displayed on Traffic card")
    public void frequencyInputDisplayed() {
        login();
        driver.get(BASE_URL + "/settings");
        WebElement freqInput = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.cssSelector("input.freq-input")));
        Assert.assertTrue(freqInput.isDisplayed());
    }

    @Test(description = "TC-043 | Set button is displayed on settings card")
    public void setButtonDisplayed() {
        login();
        driver.get(BASE_URL + "/settings");
        WebElement setBtn = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.cssSelector("button.btn-freq-set")));
        Assert.assertTrue(setBtn.isDisplayed());
    }

    @Test(description = "TC-044 | Min threshold input is displayed on settings card")
    public void minThresholdInputDisplayed() {
        login();
        driver.get(BASE_URL + "/settings");
        WebElement minInput = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.cssSelector("input.thresh-input")));
        Assert.assertTrue(minInput.isDisplayed());
    }

    @Test(description = "TC-045 | Save button is displayed on settings card")
    public void saveButtonDisplayed() {
        login();
        driver.get(BASE_URL + "/settings");
        WebElement saveBtn = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.cssSelector("button.btn-save")));
        Assert.assertTrue(saveBtn.isDisplayed());
    }

    @Test(description = "TC-046 | Reset button is displayed on settings card")
    public void resetButtonDisplayed() {
        login();
        driver.get(BASE_URL + "/settings");
        WebElement resetBtn = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.cssSelector("button.btn-reset")));
        Assert.assertTrue(resetBtn.isDisplayed());
    }

    @Test(description = "TC-047 | Changing frequency input enables Set button")
    public void changingFrequencyEnablesSetButton() {
        login();
        driver.get(BASE_URL + "/settings");
        WebElement freqInput = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.cssSelector("input.freq-input")));
        freqInput.clear();
        freqInput.sendKeys("45");
        WebElement setBtn = driver.findElement(By.cssSelector("button.btn-freq-set"));
        Assert.assertFalse(setBtn.getAttribute("disabled") != null);
    }

    @Test(description = "TC-048 | Changing Min threshold input enables Save button")
    public void changingThresholdEnablesSaveButton() {
        login();
        driver.get(BASE_URL + "/settings");
        java.util.List<WebElement> threshInputs = wait.until(ExpectedConditions
                .visibilityOfAllElementsLocatedBy(By.cssSelector("input.thresh-input")));
        WebElement minInput = threshInputs.get(0);
        minInput.clear();
        minInput.sendKeys("10");
        WebElement saveBtn = driver.findElement(By.cssSelector("button.btn-save"));
        Assert.assertFalse(saveBtn.getAttribute("disabled") != null);
    }

    @Test(description = "TC-049 | Alert above toggle checkbox is displayed")
    public void alertAboveToggleDisplayed() {
        login();
        driver.get(BASE_URL + "/settings");
        WebElement toggle = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.cssSelector("input[type='checkbox']")));
        Assert.assertTrue(toggle.isDisplayed());
    }

    @Test(description = "TC-050 | Alert above toggle can be clicked")
    public void alertAboveToggleClickable() {
        login();
        driver.get(BASE_URL + "/settings");
        WebElement toggle = wait.until(ExpectedConditions
                .elementToBeClickable(By.cssSelector("input[type='checkbox']")));
        boolean before = toggle.isSelected();
        toggle.click();
        Assert.assertNotEquals(toggle.isSelected(), before);
    }
}