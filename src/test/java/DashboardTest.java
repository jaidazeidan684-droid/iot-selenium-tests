import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;

public class DashboardTest extends BaseTest {

    @Test(description = "TC-014 | Dashboard loads with Traffic section")
    public void dashboardHasTrafficSection() {
        login();
        WebElement traffic = wait.until(org.openqa.selenium.support.ui.ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//span[@class='section-title' and contains(text(),'Traffic')]")));
        Assert.assertTrue(traffic.isDisplayed());
    }

    @Test(description = "TC-015 | Dashboard loads with Air Pollution section")
    public void dashboardHasAirPollutionSection() {
        login();
        WebElement air = wait.until(org.openqa.selenium.support.ui.ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//span[@class='section-title' and contains(text(),'Air Pollution')]")));
        Assert.assertTrue(air.isDisplayed());
    }

    @Test(description = "TC-016 | Dashboard loads with Street Lights section")
    public void dashboardHasStreetLightsSection() {
        login();
        WebElement lights = wait.until(org.openqa.selenium.support.ui.ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//span[@class='section-title' and contains(text(),'Street Lights')]")));
        Assert.assertTrue(lights.isDisplayed());
    }

    @Test(description = "TC-017 | Dashboard has sensor cards")
    public void dashboardHasSensorCards() {
        login();
        wait.until(org.openqa.selenium.support.ui.ExpectedConditions
                .visibilityOfElementLocated(By.cssSelector(".cards-grid")));
        List<WebElement> cards = driver.findElements(By.cssSelector(".card-clickable"));
        Assert.assertTrue(cards.size() >= 2);
    }

    @Test(description = "TC-018 | Congestion badge is displayed")
    public void congestionBadgeIsDisplayed() {
        login();
        WebElement badge = wait.until(org.openqa.selenium.support.ui.ExpectedConditions
                .visibilityOfElementLocated(By.cssSelector(".congestion-badge")));
        Assert.assertTrue(badge.isDisplayed());
        Assert.assertFalse(badge.getText().isEmpty());
    }

    @Test(description = "TC-019 | Clicking sensor card opens modal")
    public void clickingSensorCardOpensModal() {
        login();
        WebElement card = wait.until(org.openqa.selenium.support.ui.ExpectedConditions
                .elementToBeClickable(By.cssSelector(".card-clickable")));
        card.click();
        WebElement modal = wait.until(org.openqa.selenium.support.ui.ExpectedConditions
                .visibilityOfElementLocated(By.cssSelector("app-sensor-detail-modal")));
        Assert.assertTrue(modal.isDisplayed());
    }

    @Test(description = "TC-020 | Unauthenticated access to dashboard redirects to login")
    public void unauthenticatedDashboardRedirects() {
        driver.get(BASE_URL + "/dashboard");
        wait.until(org.openqa.selenium.support.ui.ExpectedConditions.urlContains("/login"));
        Assert.assertTrue(driver.getCurrentUrl().contains("/login"));
    }
}