package ilia.nemankov.tests;

import ilia.nemankov.ConfigurationProvider;
import ilia.nemankov.pages.LoginPage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LoginTest {

    private WebDriver driver;
    private LoginPage loginPage;
    private WebDriverWait wait;
    private ConfigurationProvider configurationProvider;

    @BeforeAll
    public void init() {
        configurationProvider = ConfigurationProvider.getInstance();
        if (configurationProvider.getProperties().getProperty("current_driver").equals("chrome")) {
            System.setProperty("webdriver.chrome.driver", configurationProvider.getProperties().getProperty("chrome_driver_path"));
            driver = new ChromeDriver();
        } else {
            System.setProperty("webdriver.gecko.driver", configurationProvider.getProperties().getProperty("mozilla_driver_path"));
            driver = new FirefoxDriver();
        }

        driver.manage().window().maximize();

        loginPage = new LoginPage(driver);

        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void loginTest() throws InterruptedException {
        driver.get("https://smi2.net/dashboard/login");

        wait.until(presenceOfElementLocated(By.xpath(LoginPage.EMAIL_FIELD_XPATH)));

        loginPage.inputEmail(configurationProvider.getProperties().getProperty("email"));

        loginPage.inputPassword(configurationProvider.getProperties().getProperty("password"));

        loginPage.getLoginButton().click();

        wait.until(presenceOfElementLocated(By.xpath(LoginPage.CAPTCHA_FRAME_XPATH)));

        loginPage.waitCaptcha();

        loginPage.getLoginButton().click();

        wait.until(presenceOfElementLocated(By.xpath(LoginPage.USERNAME_XPATH)));

        assertEquals("СМИ2 - Обмен трафиком", driver.getTitle());
    }
}
