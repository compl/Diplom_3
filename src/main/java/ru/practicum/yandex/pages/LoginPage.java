package ru.practicum.yandex.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.practicum.yandex.EnvConfig;

import java.time.Duration;

public class LoginPage {
    public static final String LOGIN_URL = EnvConfig.BASE_URL + "login";
    private final WebDriver driver;
    private final By emailField = By.xpath(".//input[@name='name']");
    private final By passwordField = By.xpath(".//input[@name='Пароль']");
    private final By logInButton = By.xpath(".//button[text()='Войти']");
    private final By signUpLink = By.xpath(".//a[@href='/register']");
    private final By resetPasswordLink = By.xpath(".//a[@href='/forgot-password']");
    private final By stellarBurgersLogo = By.className("AppHeader_header__logo__2D0X2");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public LoginPage open() {
        driver.get(LOGIN_URL);
        return this;
    }

    public void setEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    public void setPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public MainPage clickLogInButton() {
        driver.findElement(logInButton).click();
        return new MainPage(driver);
    }

    public RegistrationFormPage clickSignUpLink() {
        driver.findElement(signUpLink).click();
        return new RegistrationFormPage(driver);
    }

    public RestorePasswordPage clickResetPasswordLink() {
        driver.findElement(resetPasswordLink).click();
        return new RestorePasswordPage(driver);
    }

    public void clickStellarLogo() {
        driver.findElement(stellarBurgersLogo).click();
    }

    public LoginPage waitForLoadLoginPage() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//h2[text()='Вход']")));
        return this;
    }

    public LoginPage fillLoginForm(String email, String password) {
        setEmail(email);
        setPassword(password);
        return this;
    }
}
