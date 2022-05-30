package pages;

import configuration.models.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginPage extends BasePage {

    private static Logger log = LoggerFactory.getLogger("LoginPage.class");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "[name='email']")
    private WebElement email;

    @FindBy(css = "[name='password']")
    private WebElement password;

    @FindBy(id = "submit-login")
    private WebElement signInButton;

    @FindBy(css = ".no-account a")
    private WebElement createAccountLink;


    public LoginPage goToRegistrationForm() {
        clickOnElement(signInButton);
        clickOnElement(createAccountLink);
        return this;
    }
}
