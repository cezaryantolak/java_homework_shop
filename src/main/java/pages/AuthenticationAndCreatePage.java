package pages;

import configuration.models.User;
import configuration.models.UserFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class AuthenticationAndCreatePage extends BasePage {

    private static Logger log = LoggerFactory.getLogger("AuthenticationAndCreatePage.class");

    public AuthenticationAndCreatePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".custom-radio")
    private List<WebElement> socialTitleRadio;

    @FindBy(css = "[name='firstname']")
    private WebElement firstName;

    @FindBy(css = "[name='lastname']")
    private WebElement lastName;

    @FindBy(css = "[name='email']")
    private WebElement email;

    @FindBy(css = "[name='password']")
    private WebElement password;

    @FindBy(css = "[name='birthday']")
    private WebElement birthday;

    @FindBy(css = ".custom-checkbox")
    private List<WebElement> checkBoxValueList;

    @FindBy(css = ".custom-checkbox input")
    private List<WebElement> checkBoxList;

    @FindBy(css = ".custom-checkbox input[required]")
    private List<WebElement> requiredCheckBoxList;

    @FindBy(css = ".form-control-submit")
    private WebElement submitButton;

    public AuthenticationAndCreatePage fillRegisterForm(User user) {
        getRandomElement(socialTitleRadio);
        sendKeysToElement(firstName, user.getFirstName());
        sendKeysToElement(lastName, user.getLastName());
        sendKeysToElement(email, user.getEmail());
        sendKeysToElement(password, user.getPassword());
        sendKeysToElement(birthday, user.getBirthDate());
        checkAllCheckBoxes();
        return this;
    }

    public String getUserData() {
        String user = getAttributeValue(firstName, "value") + " " + getAttributeValue(lastName, "value");
        log.info("User name and lastname: " + user);
        return user;
    }

    public void checkAllCheckBoxes() {
        for (WebElement check : checkBoxValueList) {
            hoverToElement(check);
            WebElement checkInput = check.findElement(By.cssSelector(".custom-checkbox input"));
            checkInput.click();
        }
    }

    public void submitRegistrationForm() {

        clickOnElement(submitButton);
    }
}
