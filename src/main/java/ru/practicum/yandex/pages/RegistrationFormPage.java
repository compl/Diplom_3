package ru.practicum.yandex.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.practicum.yandex.EnvConfig;

import java.time.Duration;

public class RegistrationFormPage {
    private final static String REGISTRATION_URL = EnvConfig.BASE_URL + "register";
    private final WebDriver driver;
    private final By nameField = By.xpath(".//fieldset[1]//input");
    private final By emailField = By.xpath(".//fieldset[2]//input");
    private final By passwordField = By.xpath(".//fieldset[3]//input");
    private final By signUpButton = By.xpath(".//button[text()='Зарегистрироваться']");
    private final By loginLink = By.xpath(".//a[@href='/login']");
    private final By errorMessageForPassword = By.cssSelector(".input__error");

    public RegistrationFormPage(WebDriver driver) {
        this.driver = driver;
    }

    public RegistrationFormPage open() {
        driver.get(REGISTRATION_URL);
        return this;
    }

    public void setName(String name) {
        driver.findElement(nameField).sendKeys(name);
    }

    public void setEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    public void setPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public LoginPage clickSignUpButton() {
        driver.findElement(signUpButton).click();
        return new LoginPage(driver);
    }

    public void clickLoginLink() {
        driver.findElement(loginLink).click();

    }

    public RegistrationFormPage waitForLoadRegistrationForm() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//h2[text()='Регистрация']")));
        return this;
    }

    public void fillRegistrationForm(String name, String email, String password) {
        setName(name);
        setEmail(email);
        setPassword(password);
    }

    public void waitForErrorMessage() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(errorMessageForPassword));
    }

    public String getPasswordErrorText() {
        return driver.findElement(errorMessageForPassword).getText();
    }

}

