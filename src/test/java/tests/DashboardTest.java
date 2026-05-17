package tests;

import Pages.DashboardPage;
import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DashboardTest extends BaseTest {

    @Test(description = "TC-014 | Dashboard loads with Traffic section")
    public void dashboardHasTrafficSection() {
        login();
        DashboardPage dashboardPage = new DashboardPage(driver);
        Assert.assertTrue(dashboardPage.isTrafficSectionDisplayed());
    }

    @Test(description = "TC-015 | Dashboard loads with Air Pollution section")
    public void dashboardHasAirPollutionSection() {
        login();
        DashboardPage dashboardPage = new DashboardPage(driver);
        Assert.assertTrue(dashboardPage.isAirPollutionSectionDisplayed());
    }

    @Test(description = "TC-016 | Dashboard loads with Street Lights section")
    public void dashboardHasStreetLightsSection() {
        login();
        DashboardPage dashboardPage = new DashboardPage(driver);
        Assert.assertTrue(dashboardPage.isStreetLightsSectionDisplayed());
    }

    @Test(description = "TC-017 | Dashboard has sensor cards")
    public void dashboardHasSensorCards() {
        login();
        DashboardPage dashboardPage = new DashboardPage(driver);
        Assert.assertTrue(dashboardPage.getSensorCardCount() >= 2);
    }

    @Test(description = "TC-018 | Congestion badge is displayed")
    public void congestionBadgeIsDisplayed() {
        login();
        DashboardPage dashboardPage = new DashboardPage(driver);
        Assert.assertTrue(dashboardPage.isCongestionBadgeDisplayed());
        Assert.assertFalse(dashboardPage.getCongestionBadgeText().isEmpty());
    }

    @Test(description = "TC-019 | Clicking sensor card opens modal")
    public void clickingSensorCardOpensModal() {
        login();
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.clickFirstSensorCard();
        Assert.assertTrue(dashboardPage.isModalDisplayed());
    }

    @Test(description = "TC-020 | Unauthenticated access to dashboard redirects to login")
    public void unauthenticatedDashboardRedirects() {
        driver.get(BASE_URL + "/dashboard");
        wait.until(ExpectedConditions.urlContains("/login"));
        Assert.assertTrue(driver.getCurrentUrl().contains("/login"));
    }

    @Test(description = "TC-054 | Modal close button closes the modal")
    public void modalCloseButtonClosesModal() {
        login();
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.clickFirstSensorCard();
        Assert.assertTrue(dashboardPage.isModalDisplayed());
        WebElement closeBtn = wait.until(ExpectedConditions
                .elementToBeClickable(By.cssSelector("button.modal-close")));
        closeBtn.click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.modal-backdrop")));
        Assert.assertTrue(driver.findElements(By.cssSelector("div.modal-backdrop")).isEmpty());
    }

    @Test(description = "TC-055 | Modal displays sensor title")
    public void modalDisplaysSensorTitle() {
        login();
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.clickFirstSensorCard();
        WebElement modalTitle = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.cssSelector("div.modal-title")));
        Assert.assertTrue(modalTitle.isDisplayed());
        Assert.assertFalse(modalTitle.getText().isEmpty());
    }

    @Test(description = "TC-056 | Modal displays sensor ID")
    public void modalDisplaysSensorId() {
        login();
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.clickFirstSensorCard();
        WebElement modalIds = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.cssSelector("div.modal-ids")));
        Assert.assertTrue(modalIds.isDisplayed());
        Assert.assertFalse(modalIds.getText().isEmpty());
    }

    @Test(description = "TC-057 | Modal displays chart")
    public void modalDisplaysChart() {
        login();
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.clickFirstSensorCard();
        WebElement chart = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.cssSelector("svg.modal-chart")));
        Assert.assertTrue(chart.isDisplayed());
    }

    @Test(description = "TC-058 | Modal displays history table")
    public void modalDisplaysHistoryTable() {
        login();
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.clickFirstSensorCard();
        WebElement table = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.cssSelector("table.history-table")));
        Assert.assertTrue(table.isDisplayed());
    }

    @Test(description = "TC-059 | Modal closes when clicking backdrop")
    public void modalClosesOnBackdropClick() {
        login();
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.clickFirstSensorCard();
        Assert.assertTrue(dashboardPage.isModalDisplayed());
        WebElement backdrop = wait.until(ExpectedConditions
                .elementToBeClickable(By.cssSelector("div.modal-backdrop")));
        org.openqa.selenium.interactions.Actions actions = new org.openqa.selenium.interactions.Actions(driver);
        actions.moveToElement(backdrop, 5, 5).click().perform();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.modal-backdrop")));
        Assert.assertTrue(driver.findElements(By.cssSelector("div.modal-backdrop")).isEmpty());
    }

    @Test(description = "TC-060 | Street Lights status badge is displayed")
    public void streetLightsStatusBadgeDisplayed() {
        login();
        WebElement badge = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.cssSelector("div.onoff-badge")));
        Assert.assertTrue(badge.isDisplayed());
        Assert.assertFalse(badge.getText().isEmpty());
    }
}