package ru.practicum.yandex.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RestorePasswordPage {
    private final WebDriver driver;
    private final By loginLink = By.className("Auth_link__1fOlj");
    private final By restorePasswordHeader = By.xpath(".//h2[text()='Восстановление пароля']");

    public RestorePasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickLoginLink() {
        driver.findElement(loginLink).click();
    }

    public RestorePasswordPage waitForLoadRestorePasswordPage() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(restorePasswordHeader));
        return this;
    }

}
