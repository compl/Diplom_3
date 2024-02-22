package unAuthorizedUser;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.html5.WebStorage;
import ru.practicum.yandex.api.UserClient;
import ru.practicum.yandex.pages.LoginPage;
import ru.practicum.yandex.pages.MainPage;
import ru.practicum.yandex.pages.RegistrationFormPage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class UserRegistrationTest {
    private final UserClient client = new UserClient();
    private final String name = RandomStringUtils.randomAlphabetic(10);
    private final String email = RandomStringUtils.randomAlphanumeric(10) + "@ya.ru";
    private final String correctPassword = RandomStringUtils.randomAlphanumeric(10);
    private final String incorrectPassword = RandomStringUtils.randomAlphanumeric(5);
    @Rule
    public DriverRule driverRule = new DriverRule();
    private String accessToken;
    private RegistrationFormPage registrationPage;

    @Before
    public void setUp() {
        registrationPage = new RegistrationFormPage(driverRule.getDriver());
        registrationPage.open()
                .waitForLoadRegistrationForm();
    }

    @After
    public void deleteUser() {
        if (accessToken != null) {
            client.delete(accessToken);
        }
    }

    @Test
    public void registerNewUser() {
        registrationPage.fillRegistrationForm(name, email, correctPassword);

        LoginPage loginPage = registrationPage.clickSignUpButton();
        loginPage.waitForLoadLoginPage()
                .fillLoginForm(email, correctPassword);

        MainPage mainPage = loginPage.clickLogInButton();
        mainPage.waitForLoadMainPage();

        LocalStorage localStorage = ((WebStorage) driverRule.getDriver()).getLocalStorage();
        accessToken = localStorage.getItem("accessToken");
        assertNotNull(accessToken);
    }

    @Test
    public void registrationWithPasswordLessThenSixSymbols() {
        registrationPage.setPassword(incorrectPassword);
        registrationPage.clickSignUpButton();
        registrationPage.waitForErrorMessage();
        String expectedErrorMessage = "Некорректный пароль";
        String actualErrorMessage = registrationPage.getPasswordErrorText();
        assertEquals(expectedErrorMessage, actualErrorMessage);
    }
}
