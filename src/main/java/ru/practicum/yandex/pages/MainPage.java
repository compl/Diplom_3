package ru.practicum.yandex.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.practicum.yandex.EnvConfig;

import java.time.Duration;

public class MainPage {
    private final WebDriver driver;
    private final By accountButton = By.xpath(".//a[@href='/account']");
    private final By logInButton = By.cssSelector(".button_button__33qZ0");
    private final By bunsMenu = By.cssSelector(".tab_tab__1SPyG:nth-child(1)");
    private final By sauceMenu = By.cssSelector(".tab_tab__1SPyG:nth-child(2)");
    private final By fillingsMenu = By.cssSelector(".tab_tab__1SPyG:nth-child(3)");


    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public MainPage open() {
        driver.get(EnvConfig.BASE_URL);
        return this;
    }

    public MyAccountPage clickAccountButtonWithLoggedUser() {
        driver.findElement(accountButton).click();
        return new MyAccountPage(driver);
    }

    public LoginPage clickAccountButtonWithoutLoggedUser() {
        driver.findElement(accountButton).click();
        return new LoginPage(driver);
    }

    public LoginPage clickLogInButton() {
        driver.findElement(logInButton).click();
        return new LoginPage(driver);
    }

    public void selectBunsMenu() {
        driver.findElement(bunsMenu).click();
    }

    public void selectSauceMenu() {
        driver.findElement(sauceMenu).click();
    }

    public void selectFillingsMenu() {
        driver.findElement(fillingsMenu).click();
    }

    public void waitForLoadMainPage() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(bunsMenu));
    }

    public String getTextFromLoginButton() {
        return driver.findElement(logInButton).getText();
    }

    public boolean checkMainPageLoaded() {
        return driver.findElement(bunsMenu).isDisplayed();
    }

    public boolean checkSwitchToSauceMenu() {
        return new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.attributeContains(sauceMenu, "class", "current"));
    }

    public boolean checkSwitchToFillingsMenu() {
        return new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.attributeContains(fillingsMenu, "class", "current"));
    }

    public boolean checkSwitchToBunsMenu() {
        return new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.attributeContains(bunsMenu, "class", "current"));
    }
}

