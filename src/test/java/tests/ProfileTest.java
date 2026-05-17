package tests;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;

public class ProfileTest extends BaseTest {

    @Test(description = "TC-061 | Profile link in sidebar navigates to profile page")
    public void profileLinkNavigates() {
        login();
        WebElement profileLink = wait.until(ExpectedConditions
                .elementToBeClickable(By.cssSelector("a.nav-item[href='/profile']")));
        org.openqa.selenium.JavascriptExecutor js = (org.openqa.selenium.JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", profileLink);
        wait.until(ExpectedConditions.urlContains("/profile"));
        Assert.assertTrue(driver.getCurrentUrl().contains("/profile"));
    }

    @Test(description = "TC-062 | Profile page loads with user full name field")
    public void profilePageHasFullNameField() {
        login();
        driver.get(BASE_URL + "/profile");
        WebElement fullName = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.cssSelector("input.field-input[type='text']")));
        Assert.assertTrue(fullName.isDisplayed());
        Assert.assertFalse(fullName.getAttribute("value").isEmpty());
    }

    @Test(description = "TC-063 | Profile page loads with email field")
    public void profilePageHasEmailField() {
        login();
        driver.get(BASE_URL + "/profile");
        WebElement emailField = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.cssSelector("input.field-input[type='email']")));
        Assert.assertTrue(emailField.isDisplayed());
        Assert.assertFalse(emailField.getAttribute("value").isEmpty());
    }

    @Test(description = "TC-064 | Full name and email fields are read-only")
    public void profileFieldsAreReadOnly() {
        login();
        driver.get(BASE_URL + "/profile");
        WebElement fullName = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.cssSelector("input.field-input[type='text']")));
        WebElement email = driver.findElement(By.cssSelector("input.field-input[type='email']"));
        Assert.assertNotNull(fullName.getAttribute("readonly"));
        Assert.assertNotNull(email.getAttribute("readonly"));
    }

    @Test(description = "TC-065 | Upload profile photo button is displayed")
    public void uploadProfilePhotoButtonDisplayed() {
        login();
        driver.get(BASE_URL + "/profile");
        WebElement uploadBtn = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//button[contains(@class,'btn-action') and contains(.,'Upload profile photo')]")));
        Assert.assertTrue(uploadBtn.isDisplayed());
    }

    @Test(description = "TC-066 | Change password button is displayed")
    public void changePasswordButtonDisplayed() {
        login();
        driver.get(BASE_URL + "/profile");
        WebElement changePwBtn = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//button[contains(@class,'btn-action') and contains(.,'Change password')]")));
        Assert.assertTrue(changePwBtn.isDisplayed());
    }

    @Test(description = "TC-067 | Clicking Upload profile photo opens picture panel")
    public void uploadPhotoButtonOpensPicturePanel() {
        login();
        driver.get(BASE_URL + "/profile");
        WebElement uploadBtn = wait.until(ExpectedConditions
                .elementToBeClickable(By.xpath("//button[contains(@class,'btn-action') and contains(.,'Upload profile photo')]")));
        uploadBtn.click();
        WebElement panel = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.cssSelector("div.panel")));
        Assert.assertTrue(panel.isDisplayed());
        WebElement panelTitle = driver.findElement(By.cssSelector("p.panel-title"));
        Assert.assertEquals(panelTitle.getText(), "Update Profile Picture");
    }

    @Test(description = "TC-068 | Picture panel has Choose File button")
    public void picturePanelHasChooseFileButton() {
        login();
        driver.get(BASE_URL + "/profile");
        WebElement uploadBtn = wait.until(ExpectedConditions
                .elementToBeClickable(By.xpath("//button[contains(@class,'btn-action') and contains(.,'Upload profile photo')]")));
        uploadBtn.click();
        WebElement chooseFile = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.cssSelector("label.upload-btn")));
        Assert.assertTrue(chooseFile.isDisplayed());
    }

    @Test(description = "TC-069 | Picture panel has Save button disabled when no file selected")
    public void picturePanelSaveButtonDisabledWithNoFile() {
        login();
        driver.get(BASE_URL + "/profile");
        WebElement uploadBtn = wait.until(ExpectedConditions
                .elementToBeClickable(By.xpath("//button[contains(@class,'btn-action') and contains(.,'Upload profile photo')]")));
        uploadBtn.click();
        WebElement saveBtn = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.cssSelector("button.btn-panel-save")));
        Assert.assertNotNull(saveBtn.getAttribute("disabled"));
    }

    @Test(description = "TC-070 | Picture panel Cancel button closes the panel")
    public void picturePanelCancelButtonClosesPanel() {
        login();
        driver.get(BASE_URL + "/profile");
        WebElement uploadBtn = wait.until(ExpectedConditions
                .elementToBeClickable(By.xpath("//button[contains(@class,'btn-action') and contains(.,'Upload profile photo')]")));
        uploadBtn.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.panel")));
        WebElement cancelBtn = driver.findElement(By.cssSelector("button.btn-panel-cancel"));
        cancelBtn.click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.panel")));
        Assert.assertTrue(driver.findElements(By.cssSelector("div.panel")).isEmpty());
    }

    @Test(description = "TC-071 | Clicking Change password opens password panel")
    public void changePasswordButtonOpensPasswordPanel() {
        login();
        driver.get(BASE_URL + "/profile");
        WebElement changePwBtn = wait.until(ExpectedConditions
                .elementToBeClickable(By.xpath("//button[contains(@class,'btn-action') and contains(.,'Change password')]")));
        changePwBtn.click();
        WebElement panel = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.cssSelector("div.panel")));
        Assert.assertTrue(panel.isDisplayed());
        WebElement panelTitle = driver.findElement(By.cssSelector("p.panel-title"));
        Assert.assertEquals(panelTitle.getText(), "Change Password");
    }

    @Test(description = "TC-072 | Password panel has current password field")
    public void passwordPanelHasCurrentPasswordField() {
        login();
        driver.get(BASE_URL + "/profile");
        WebElement changePwBtn = wait.until(ExpectedConditions
                .elementToBeClickable(By.xpath("//button[contains(@class,'btn-action') and contains(.,'Change password')]")));
        changePwBtn.click();
        WebElement currentPw = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.cssSelector("input.panel-input[placeholder='Current password']")));
        Assert.assertTrue(currentPw.isDisplayed());
    }

    @Test(description = "TC-073 | Password panel has new password field")
    public void passwordPanelHasNewPasswordField() {
        login();
        driver.get(BASE_URL + "/profile");
        WebElement changePwBtn = wait.until(ExpectedConditions
                .elementToBeClickable(By.xpath("//button[contains(@class,'btn-action') and contains(.,'Change password')]")));
        changePwBtn.click();
        WebElement newPw = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.cssSelector("input.panel-input[placeholder='New password']")));
        Assert.assertTrue(newPw.isDisplayed());
    }

    @Test(description = "TC-074 | Password panel has confirm new password field")
    public void passwordPanelHasConfirmPasswordField() {
        login();
        driver.get(BASE_URL + "/profile");
        WebElement changePwBtn = wait.until(ExpectedConditions
                .elementToBeClickable(By.xpath("//button[contains(@class,'btn-action') and contains(.,'Change password')]")));
        changePwBtn.click();
        WebElement confirmPw = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.cssSelector("input.panel-input[placeholder='Confirm new password']")));
        Assert.assertTrue(confirmPw.isDisplayed());
    }

    @Test(description = "TC-075 | Password panel eye toggle works for current password")
    public void passwordPanelCurrentPasswordToggleWorks() {
        login();
        driver.get(BASE_URL + "/profile");
        WebElement changePwBtn = wait.until(ExpectedConditions
                .elementToBeClickable(By.xpath("//button[contains(@class,'btn-action') and contains(.,'Change password')]")));
        changePwBtn.click();
        WebElement currentPw = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.cssSelector("input.panel-input[placeholder='Current password']")));
        currentPw.sendKeys("testpassword");
        Assert.assertEquals(currentPw.getAttribute("type"), "password");
        List<WebElement> eyeBtns = driver.findElements(By.cssSelector("button.pw-eye-btn"));
        eyeBtns.get(0).click();
        Assert.assertEquals(currentPw.getAttribute("type"), "text");
    }

    @Test(description = "TC-076 | Password mismatch shows error in password panel")
    public void passwordPanelMismatchShowsError() {
        login();
        driver.get(BASE_URL + "/profile");
        WebElement changePwBtn = wait.until(ExpectedConditions
                .elementToBeClickable(By.xpath("//button[contains(@class,'btn-action') and contains(.,'Change password')]")));
        changePwBtn.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.panel")));
        driver.findElement(By.cssSelector("input.panel-input[placeholder='Current password']")).sendKeys("oldpass");
        driver.findElement(By.cssSelector("input.panel-input[placeholder='New password']")).sendKeys("NewPass123!");
        driver.findElement(By.cssSelector("input.panel-input[placeholder='Confirm new password']")).sendKeys("DifferentPass!");
        WebElement error = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.cssSelector("div.panel-error")));
        Assert.assertTrue(error.isDisplayed());
        Assert.assertTrue(error.getText().contains("match"));
    }

    @Test(description = "TC-077 | Password panel Save button disabled when fields empty")
    public void passwordPanelSaveDisabledWhenEmpty() {
        login();
        driver.get(BASE_URL + "/profile");
        WebElement changePwBtn = wait.until(ExpectedConditions
                .elementToBeClickable(By.xpath("//button[contains(@class,'btn-action') and contains(.,'Change password')]")));
        changePwBtn.click();
        WebElement saveBtn = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.cssSelector("button.btn-panel-save")));
        Assert.assertNotNull(saveBtn.getAttribute("disabled"));
    }

    @Test(description = "TC-078 | Password panel Cancel button closes the panel")
    public void passwordPanelCancelButtonClosesPanel() {
        login();
        driver.get(BASE_URL + "/profile");
        WebElement changePwBtn = wait.until(ExpectedConditions
                .elementToBeClickable(By.xpath("//button[contains(@class,'btn-action') and contains(.,'Change password')]")));
        changePwBtn.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.panel")));
        WebElement cancelBtn = driver.findElement(By.cssSelector("button.btn-panel-cancel"));
        cancelBtn.click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.panel")));
        Assert.assertTrue(driver.findElements(By.cssSelector("div.panel")).isEmpty());
    }

    @Test(description = "TC-079 | Unauthenticated access to profile redirects to login")
    public void unauthenticatedProfileRedirects() {
        driver.get(BASE_URL + "/profile");
        wait.until(ExpectedConditions.urlContains("/login"));
        Assert.assertTrue(driver.getCurrentUrl().contains("/login"));
    }

    @Test(description = "TC-080 | Profile page shows user name in header")
    public void profilePageShowsUserName() {
        login();
        driver.get(BASE_URL + "/profile");
        WebElement profileName = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.cssSelector("h2.profile-name")));
        Assert.assertTrue(profileName.isDisplayed());
        Assert.assertFalse(profileName.getText().isEmpty());
    }
}