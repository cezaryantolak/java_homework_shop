package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FooterPage extends BasePage {

    private static Logger log = LoggerFactory.getLogger("FooterPage.class");

    public FooterPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "#link-product-page-prices-drop-1")
    private WebElement priceDropButton;

    @FindBy(css = "#footer_account_list > li:nth-child(4) > a")
    private WebElement addressButton;

    public FooterPage clickPriceDropButton() {
        clickOnElement(priceDropButton);
        return this;
    }
}
