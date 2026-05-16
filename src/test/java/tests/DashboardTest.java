package tests;

import Pages.DashboardPage;
import base.BaseTest;
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
        wait.until(org.openqa.selenium.support.ui.ExpectedConditions.urlContains("/login"));
        Assert.assertTrue(driver.getCurrentUrl().contains("/login"));
    }
}