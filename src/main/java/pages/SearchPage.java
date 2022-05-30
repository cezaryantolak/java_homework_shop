package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class SearchPage extends BasePage {

    private static Logger log = LoggerFactory.getLogger("SearchPage.class");
    HomePage homePage = new HomePage(driver);
    HeaderPage headerPage = new HeaderPage(driver);

    public SearchPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "div.product-description")
    private WebElement searchResultProductName;

    @FindBy(css = "#ui-id-1")
    private WebElement dropDownResult;

    @FindBy(css = "#products")
    private WebElement listOfSearchProduct;

    String product = homePage.randomProductNameFromHomePage();

    public SearchPage fillSearchWindow(String name) {
        sendKeysToElement(headerPage.getSearchWindow(), name);
        return this;
    }

    public void clickSearchButton() {
        clickOnElement(headerPage.getSearchButton());
    }

    public String getTextFromSearchWindow() {

        return getTextFromElement(searchResultProductName);
    }

    public String getTextFromSearchWindowAfterSearch() {

        return getTextFromElement(searchResultProductName);
    }

    public String getProductNameFromDropDownList() {

        return getTextFromElement(dropDownResult);
    }

    public SearchPage fillSearchWindowWithRandomProduct() {
        log.info("Selected random product name is: " + product);
        fillSearchWindow(product);
        return this;
    }

    public String getProduct() {

        return product;
    }
}
