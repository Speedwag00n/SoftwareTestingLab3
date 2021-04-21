package ilia.nemankov.pages;

import lombok.Getter;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Getter
public class MainPage {

    public static final String PAGE_URL = "https://smi2.ru";

    public static final String MAIN_CATEGORY_XPATH = "//div[@id='navigation']/nav/div/div/div/div/a";
    public static final String SPB_CATEGORY_XPATH = "//div[@id='navigation']/nav/div/div/div/div[2]/a";
    public static final String CORONA_CATEGORY_XPATH = "//div[@id='navigation']/nav/div/div/div/div[3]/a";
    public static final String POLITICS_CATEGORY_XPATH = "//div[@id='navigation']/nav/div/div/div/div[4]/a";
    public static final String SPORTS_CATEGORY_XPATH = "//div[@id='navigation']/nav/div/div/div/div[5]/a";
    public static final String SCIENCE_CATEGORY_XPATH = "//div[@id='navigation']/nav/div/div/div/div[6]/a";
    public static final String BUSINESS_CATEGORY_XPATH = "//div[@id='navigation']/nav/div/div/div/div[7]/a";

    public static final String MORE_CATEGORIES_XPATH = "//div[@id='navigation']/nav/div/div/div[2]/div/div/div/div";

    public static final String TECHNOLOGIES_CATEGORY_XPATH = "//div[@id='navigation']/nav/div/div/div[2]/div/div[2]/div/a";
    public static final String AUTOS_CATEGORY_XPATH = "//div[@id='navigation']/nav/div/div/div[2]/div/div[2]/div[2]/a";
    public static final String SHOW_BUSINESS_CATEGORY_XPATH = "//div[@id='navigation']/nav/div/div/div[2]/div/div[2]/div[3]/a";

    public static final String SEARCH_ELEMENT_XPATH = "//div[@id='content']/div/div/div[2]/div/div/input";
    public static final String CLEAR_SEARCH_ELEMENT_XPATH = "//div[@id='content']/div[1]/div/div/div/button";
    public static final String FIRST_FOUND_ARTICLE_XPATH = "//div[@id='content']/div[2]/div[2]/a[2]/div/div/div/p";

    public static final String FIRST_NEW_XPATH = "//div[@id='content']/div[3]/div[3]/div/div/a[2]/span";
    public static final String FIRST_NEW_FULL_BUTTON_XPATH = "//div[@id='content']/div[3]/div[3]/div/div[3]/div/button";
    public static final String READ_MORE_BUTTON_XPATH = "//a/div/div[4]/button";

    public static final String SOURCES_BUTTON_XPATH = "//div[3]/div/div/div/div/button/span";
    public static final String ENABLE_ALL_SOURCES_BUTTON_XPATH = "//div[@id='modal']/div[2]/div[2]/div[2]/button";
    public static final String SEARCH_SOURCE_ELEMENT_XPATH = "//div[@id='modal']/div[2]/div[2]/div/input";
    public static final String ENABLE_FIRST_SOURCE_SWITCH_XPATH = "//div[@id='modal']/div[2]/div[3]/div/div/div/div/div/div/div";

    public static final String OPEN_ALL_BUTTON_XPATH = "//div[@id='content']/div[3]/div/div/a";
    public static final String REGION_XPATH = "//div[@id='navigation']/div/div/div[2]/div/div[6]/button/span";

    private final WebDriver driver;

    @FindBy(xpath = MAIN_CATEGORY_XPATH)
    private WebElement mainCategory;

    @FindBy(xpath = SPB_CATEGORY_XPATH)
    private WebElement spbCategory;

    @FindBy(xpath = CORONA_CATEGORY_XPATH)
    private WebElement coronaCategory;

    @FindBy(xpath = POLITICS_CATEGORY_XPATH)
    private WebElement politicsCategory;

    @FindBy(xpath = SPORTS_CATEGORY_XPATH)
    private WebElement sportsCategory;

    @FindBy(xpath = SCIENCE_CATEGORY_XPATH)
    private WebElement scienceCategory;

    @FindBy(xpath = BUSINESS_CATEGORY_XPATH)
    private WebElement businessCategory;

    @FindBy(xpath = MORE_CATEGORIES_XPATH)
    private WebElement moreCategories;

    @FindBy(xpath = TECHNOLOGIES_CATEGORY_XPATH)
    private WebElement technologiesCategory;

    @FindBy(xpath = AUTOS_CATEGORY_XPATH)
    private WebElement autosCategory;

    @FindBy(xpath = SHOW_BUSINESS_CATEGORY_XPATH)
    private WebElement showBusinessCategory;

    @FindBy(xpath = SEARCH_ELEMENT_XPATH)
    private WebElement searchElement;

    @FindBy(xpath = CLEAR_SEARCH_ELEMENT_XPATH)
    private WebElement clearSearchElement;

    @FindBy(xpath = FIRST_FOUND_ARTICLE_XPATH)
    private WebElement firstFoundArticle;

    @FindBy(xpath = FIRST_NEW_FULL_BUTTON_XPATH)
    private WebElement firstNewFullButton;

    @FindBy(xpath = READ_MORE_BUTTON_XPATH)
    private WebElement readMoreButton;

    @FindBy(xpath = FIRST_NEW_XPATH)
    private WebElement firstNew;

    @FindBy(xpath = SOURCES_BUTTON_XPATH)
    private WebElement sourcesButton;

    @FindBy(xpath = SEARCH_SOURCE_ELEMENT_XPATH)
    private WebElement searchSourceElement;

    @FindBy(xpath = ENABLE_FIRST_SOURCE_SWITCH_XPATH)
    private WebElement enableFirstSourceSwitch;

    @FindBy(xpath = ENABLE_ALL_SOURCES_BUTTON_XPATH)
    private WebElement enableAllSourcesButton;

    @FindBy(xpath = OPEN_ALL_BUTTON_XPATH)
    private WebElement openAllButton;

    @FindBy(xpath = REGION_XPATH)
    private WebElement region;

    public MainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void searchNewByText(String text) {
        searchElement.sendKeys(text, Keys.ENTER);
    }

    public void searchSourceByText(String text) {
        searchSourceElement.sendKeys(text, Keys.ENTER);
    }

}
