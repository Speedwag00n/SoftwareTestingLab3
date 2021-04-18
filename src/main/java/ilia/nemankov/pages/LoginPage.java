package ilia.nemankov.pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Getter
public class LoginPage {

    private final WebDriver driver;

    public static final String EMAIL_FIELD_XPATH = "//input[@id='vars.user.email']";
    public static final String PASSWORD_FIELD_XPATH = "//input[@id='vars.user.password']";
    public static final String LOGIN_BUTTON_XPATH = "/html/body/div[1]/ui-view/div[1]/div/div[2]/form/button";
    public static final String CAPTCHA_FRAME_XPATH = "/html/body/div[1]/ui-view/div[1]/div/div[2]/form/div[4]/div/div/div/iframe";
    public static final String USERNAME_XPATH = "/html/body/div[1]/div[1]/div[1]/div/div[3]/div/lx-dropdown/div/div[1]/div";

    @FindBy(xpath = EMAIL_FIELD_XPATH)
    private WebElement emailField;

    @FindBy(xpath = PASSWORD_FIELD_XPATH)
    private WebElement passwordField;

    @FindBy(xpath = LOGIN_BUTTON_XPATH)
    private WebElement loginButton;

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void inputEmail(String email) {
        emailField.sendKeys(email);
    }

    public void inputPassword(String password) {
        passwordField.sendKeys(password);
    }

    public void waitCaptcha() throws InterruptedException {
        Thread.sleep(60000);
    }

}
