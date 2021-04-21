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
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

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
    public void clearAndSearchTest() {
        driver.get(MainPage.PAGE_URL);

        wait.until(presenceOfElementLocated(By.xpath(MainPage.SEARCH_ELEMENT_XPATH)));

        mainPage.searchNewByText("Китай запустил зонд");

        wait.until(presenceOfElementLocated(By.xpath(MainPage.FIRST_FOUND_ARTICLE_XPATH)));

        mainPage.getClearSearchElement().click();

        mainPage.searchNewByText("Продажи туров в Сочи выросли");

        wait.until(presenceOfElementLocated(By.xpath(MainPage.FIRST_FOUND_ARTICLE_XPATH)));

        assertEquals("Продажи туров в Сочи на фоне закрытой Турции выросли на 25%", mainPage.getFirstFoundArticle().getText());
    }

}
