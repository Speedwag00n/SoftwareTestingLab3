package ilia.nemankov.tests;

import ilia.nemankov.ConfigurationProvider;
import ilia.nemankov.pages.MainPage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SearchTests {

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
    public void searchTest() {
        driver.get(MainPage.PAGE_URL);

        wait.until(presenceOfElementLocated(By.xpath(MainPage.SEARCH_ELEMENT_XPATH)));

        mainPage.searchNewByText("Китай запустил зонд");

        wait.until(presenceOfElementLocated(By.xpath(MainPage.FIRST_FOUND_ARTICLE_XPATH)));

        assertEquals("Китай запустит два зонда за пределы Солнечной системы", mainPage.getFirstFoundArticle().getText());
    }

    @Test
    public void disableSourceTest() throws InterruptedException {
        driver.get(MainPage.PAGE_URL);

        wait.until(presenceOfElementLocated(By.xpath(MainPage.SOURCES_BUTTON_XPATH)));

        mainPage.getSourcesButton().click();

        wait.until(elementToBeClickable(By.xpath(MainPage.SEARCH_SOURCE_ELEMENT_XPATH)));

        mainPage.getEnableAllSourcesButton().click();

        mainPage.searchSourceByText("RT");

        mainPage.getEnableFirstSourceSwitch().click();

        Thread.sleep(2000);

        List<WebElement> elements = driver.findElements(By.xpath("//a[contains(@href, '/publisher/236')]"));

        assertEquals(0, elements.size());
    }

}
