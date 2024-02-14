package unAuthorizedUser;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import ru.practicum.yandex.pages.MainPage;

import static org.junit.Assert.assertTrue;

public class SwitchBetweenMenuTest {
    @Rule
    public DriverRule driverRule = new DriverRule();
    MainPage mainPage;

    @Before
    public void setUp() {
        mainPage = new MainPage(driverRule.getDriver());
        mainPage.open()
                .waitForLoadMainPage();
    }

    @Test
    public void switchToSauceMenu() {
        mainPage.selectSauceMenu();
        assertTrue(mainPage.checkSwitchToSauceMenu());
    }

    @Test
    public void switchToFillingsMenu() {
        mainPage.selectFillingsMenu();
        assertTrue(mainPage.checkSwitchToFillingsMenu());
    }

    @Test
    public void switchToBunsMenu() {
        mainPage.selectSauceMenu();
        mainPage.selectBunsMenu();
        assertTrue(mainPage.checkSwitchToBunsMenu());
    }
}
