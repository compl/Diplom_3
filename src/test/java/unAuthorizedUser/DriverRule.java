package unAuthorizedUser;

import org.junit.rules.ExternalResource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import ru.practicum.yandex.EnvConfig;

import java.io.File;
import java.time.Duration;

public class DriverRule extends ExternalResource {
    WebDriver driver;

    @Override
    protected void before() throws Throwable {
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        if ("yandex".equals(System.getProperty("browser")))
            setUpYandex();
        else
            setUpChrome();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
    }

    private void setUpChrome() {
        ChromeDriverService service = new ChromeDriverService.Builder()
                .usingDriverExecutable(new File(EnvConfig.CHROME_DRIVER))
                .build();
        ChromeOptions options = new ChromeOptions()
                .setBinary(EnvConfig.CHROME_BINARY);

        driver = new ChromeDriver(service, options);
    }

    public void setUpYandex() {
        ChromeDriverService service = new ChromeDriverService.Builder()
                .usingDriverExecutable(new File(EnvConfig.YANDEX_DRIVER))
                .build();
        ChromeOptions options = new ChromeOptions()
                .setBinary(EnvConfig.YANDEX_BINARY);

        driver = new ChromeDriver(service, options);
    }

    @Override
    protected void after() {
        driver.quit();
    }

    public WebDriver getDriver() {
        return driver;
    }
}