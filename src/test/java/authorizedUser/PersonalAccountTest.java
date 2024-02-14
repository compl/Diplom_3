package authorizedUser;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.html5.WebStorage;
import ru.practicum.yandex.api.User;
import ru.practicum.yandex.api.UserClient;
import ru.practicum.yandex.pages.LoginPage;
import ru.practicum.yandex.pages.MainPage;
import ru.practicum.yandex.pages.MyAccountPage;
import unAuthorizedUser.DriverRule;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PersonalAccountTest {

    private final UserClient client = new UserClient();
    private final String email = RandomStringUtils.randomAlphanumeric(10) + "@ya.ru";
    private final String password = RandomStringUtils.randomAlphanumeric(10);
    private final String name = RandomStringUtils.randomAlphabetic(10);
    private final User user = new User(email, password, name);
    @Rule
    public DriverRule driverRule = new DriverRule();
    private String accessToken;
    private MainPage mainPage;
    private MyAccountPage accountPage;

    @Before
    public void loginUser() {
        client.createNewUser(user);

        LoginPage loginPage = new LoginPage(driverRule.getDriver());
        loginPage.open()
                .waitForLoadLoginPage()
                .fillLoginForm(user.getEmail(), user.getPassword());

        mainPage = loginPage.clickLogInButton();
        mainPage.waitForLoadMainPage();

        accountPage = mainPage.clickAccountButtonWithLoggedUser();
        accountPage.waitForLoadMyAccountPage();

        LocalStorage localStorage = ((WebStorage) driverRule.getDriver()).getLocalStorage();
        accessToken = localStorage.getItem("accessToken");
    }

    @After
    public void deleteUser() {
        client.delete(accessToken);
    }

    @Test
    public void clickStellarLogoToMoveToMainPage() {
        accountPage.clickStellarBurgersLogo();
        mainPage.waitForLoadMainPage();
        assertTrue(mainPage.checkMainPageLoaded());
    }

    @Test
    public void clickConstructorHeaderLinkToMoveToMainPage() {
        accountPage.clickConstructorHeaderLink();
        mainPage.waitForLoadMainPage();
        assertTrue(mainPage.checkMainPageLoaded());
    }

    @Test
    public void logOutOfAccount() {
        LoginPage loginPage = accountPage.clickExitButton();
        loginPage.waitForLoadLoginPage()
                .clickStellarLogo();

        mainPage.waitForLoadMainPage();
        String actualTextOnLoginButtonWhenLoggedOut = mainPage.getTextFromLoginButton();
        String expectedTextOnLoginButton = "Войти в аккаунт";
        assertEquals(expectedTextOnLoginButton, actualTextOnLoginButtonWhenLoggedOut);
    }
}
