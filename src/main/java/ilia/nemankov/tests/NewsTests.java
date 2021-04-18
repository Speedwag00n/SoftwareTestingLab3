package ilia.nemankov.tests;

import ilia.nemankov.ConfigurationProvider;
import ilia.nemankov.pages.MainPage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class NewsTests {

    private WebDriver driver;
    private MainPage mainPage;
    private WebDriverWait wait;

    @BeforeAll
    public void init() {
        ConfigurationProvider configurationProvider = ConfigurationProvider.getInstance();
        if (configurationProvider.getProperties().getProperty("current_driver").equals("chrome")) {
            System.setProperty("webdriver.chrome.driver", configurationProvider.getProperties().getProperty("chrome_driver_path"));
            driver = new ChromeDriver();
        } else {
            System.setProperty("webdriver.gecko.driver", configurationProvider.getProperties().getProperty("mozilla_driver_path"));
            driver = new FirefoxDriver();
        }

        driver.manage().window().maximize();

        mainPage = new MainPage(driver);

        wait = new WebDriverWait(driver, 10);
    }

    @AfterAll
    public void close() {
        driver.close();
    }

    @Test
    public void openNewTest() {
        driver.get(MainPage.PAGE_URL);

        wait.until(presenceOfElementLocated(By.xpath(MainPage.FIRST_NEW_XPATH)));

        String initialUrl = driver.getCurrentUrl();

        mainPage.getFirstNew().click();

        assertNotEquals(initialUrl, driver.getCurrentUrl());
    }

    @Test
    public void openFullNewTest() {
        driver.get(MainPage.PAGE_URL);

        wait.until(presenceOfElementLocated(By.xpath(MainPage.FIRST_NEW_XPATH)));

        Actions builder = new Actions(driver);
        builder.moveToElement(mainPage.getFirstNew()).perform();

        wait.until(elementToBeClickable(By.xpath(MainPage.FIRST_NEW_FULL_BUTTON_XPATH)));

        mainPage.getFirstNewFullButton().click();

        wait.until(presenceOfElementLocated(By.xpath(MainPage.READ_MORE_BUTTON_XPATH)));
    }

}
