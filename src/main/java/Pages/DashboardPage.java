package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;

public class DashboardPage extends BasePage {

    private final By trafficSection = By.xpath("//span[@class='section-title' and contains(text(),'Traffic')]");
    private final By airPollutionSection = By.xpath("//span[@class='section-title' and contains(text(),'Air Pollution')]");
    private final By streetLightsSection = By.xpath("//span[@class='section-title' and contains(text(),'Street Lights')]");
    private final By cardsGrid = By.cssSelector(".cards-grid");
    private final By sensorCards = By.cssSelector(".card-clickable");
    private final By congestionBadge = By.cssSelector(".congestion-badge");
    private final By sensorModal = By.cssSelector("div.modal-backdrop");

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    public void navigateTo(String baseUrl) {
        driver.get(baseUrl + "/dashboard");
    }

    public boolean isTrafficSectionDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(trafficSection)).isDisplayed();
    }

    public boolean isAirPollutionSectionDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(airPollutionSection)).isDisplayed();
    }

    public boolean isStreetLightsSectionDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(streetLightsSection)).isDisplayed();
    }

    public boolean isCardsGridDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(cardsGrid)).isDisplayed();
    }

    public int getSensorCardCount() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(cardsGrid));
        List<WebElement> cards = driver.findElements(sensorCards);
        return cards.size();
    }

    public boolean isCongestionBadgeDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(congestionBadge)).isDisplayed();
    }

    public String getCongestionBadgeText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(congestionBadge)).getText();
    }

    public void clickFirstSensorCard() {
        wait.until(ExpectedConditions.elementToBeClickable(sensorCards)).click();
    }

    public boolean isModalDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(sensorModal)).isDisplayed();
    }
}