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
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CategoriesTests {

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
    public void spbCategoryTest() throws InterruptedException {
        driver.get(MainPage.PAGE_URL);

        wait.until(presenceOfElementLocated(By.xpath(MainPage.SPB_CATEGORY_XPATH)));

        mainPage.getSpbCategory().click();

        Thread.sleep(1000);

        assertEquals("СМИ2 - Санкт-Петербург", driver.getTitle());
    }

    @Test
    public void coronaCategoryTest() throws InterruptedException {
        driver.get(MainPage.PAGE_URL);

        wait.until(presenceOfElementLocated(By.xpath(MainPage.CORONA_CATEGORY_XPATH)));

        mainPage.getCoronaCategory().click();

        Thread.sleep(1000);

        assertEquals("СМИ2 - Коронавирус", driver.getTitle());
    }

    @Test
    public void politicsCategoryTest() throws InterruptedException {
        driver.get(MainPage.PAGE_URL);

        wait.until(presenceOfElementLocated(By.xpath(MainPage.POLITICS_CATEGORY_XPATH)));

        mainPage.getPoliticsCategory().click();

        Thread.sleep(1000);

        assertEquals("СМИ2 - Политика", driver.getTitle());
    }

    @Test
    public void sportsCategoryTest() throws InterruptedException {
        driver.get(MainPage.PAGE_URL);

        wait.until(presenceOfElementLocated(By.xpath(MainPage.SPORTS_CATEGORY_XPATH)));

        mainPage.getSportsCategory().click();

        Thread.sleep(1000);

        assertEquals("СМИ2 - Спорт", driver.getTitle());
    }

    @Test
    public void scienceCategoryTest() throws InterruptedException {
        driver.get(MainPage.PAGE_URL);

        wait.until(presenceOfElementLocated(By.xpath(MainPage.SCIENCE_CATEGORY_XPATH)));

        mainPage.getScienceCategory().click();

        Thread.sleep(1000);

        assertEquals("СМИ2 - Наука", driver.getTitle());
    }

    @Test
    public void businessCategoryTest() throws InterruptedException {
        driver.get(MainPage.PAGE_URL);

        wait.until(presenceOfElementLocated(By.xpath(MainPage.BUSINESS_CATEGORY_XPATH)));

        mainPage.getBusinessCategory().click();

        Thread.sleep(1000);

        assertEquals("СМИ2 - Бизнес", driver.getTitle());
    }

    @Test
    public void technologiesCategoryTest() throws InterruptedException {
        driver.get(MainPage.PAGE_URL);

        wait.until(presenceOfElementLocated(By.xpath(MainPage.MORE_CATEGORIES_XPATH)));

        mainPage.getMoreCategories().click();

        wait.until(presenceOfElementLocated(By.xpath(MainPage.TECHNOLOGIES_CATEGORY_XPATH)));

        mainPage.getTechnologiesCategory().click();

        Thread.sleep(1000);

        assertEquals("СМИ2 - Технологии", driver.getTitle());
    }

    @Test
    public void autosCategoryTest() throws InterruptedException {
        driver.get(MainPage.PAGE_URL);

        wait.until(presenceOfElementLocated(By.xpath(MainPage.MORE_CATEGORIES_XPATH)));

        mainPage.getMoreCategories().click();

        wait.until(presenceOfElementLocated(By.xpath(MainPage.AUTOS_CATEGORY_XPATH)));

        mainPage.getAutosCategory().click();

        Thread.sleep(1000);

        assertEquals("СМИ2 - Авто и мото", driver.getTitle());
    }

    @Test
    public void showBusinessCategoryTest() throws InterruptedException {
        driver.get(MainPage.PAGE_URL);

        wait.until(presenceOfElementLocated(By.xpath(MainPage.MORE_CATEGORIES_XPATH)));

        mainPage.getMoreCategories().click();

        wait.until(presenceOfElementLocated(By.xpath(MainPage.SHOW_BUSINESS_CATEGORY_XPATH)));

        mainPage.getShowBusinessCategory().click();

        Thread.sleep(1000);

        assertEquals("СМИ2 - Шоу-бизнес", driver.getTitle());
    }

}
