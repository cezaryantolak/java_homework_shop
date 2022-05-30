package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class CategoryPage extends BasePage{

    private static Logger log = LoggerFactory.getLogger("CategoryPage.class");
    HeaderPage headerPage = new HeaderPage(driver);

    public CategoryPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[@itemprop='itemListElement']")
    public List<WebElement> productList;

    @FindBy(css = "#js-product-list-header > div > h1")
    public WebElement pageTitle;

    @FindBy(css = "div.col-md-6.hidden-sm-down.total-products")
    private WebElement productQuantity;

    @FindBy(xpath = "//ul[@class='category-sub-menu']//a")
    private List<WebElement> subCategories;

    private List<String> categoryNameDisplayed = new ArrayList<>();


    private void getCategoryName() {
        String categoryName = getTextFromElement(pageTitle);
        log.info("Category after click: " + categoryName);
    }

    private boolean isMenuDisplayed() {
        if (headerPage.getSideMenu().isDisplayed()) {
            log.info("Side menu is displayed");
            return true;
        } else {
            log.info("Side menu is not displayed");
            return false;
        }
    }

    private boolean countProductList() {
        if (getTextFromElement(productQuantity).contains(String.valueOf(productList.size()))) {
            log.info("Products on this page is: " + productList.size());
            return true;
        } else {
            return false;
        }
    }

    private void amountOfProducts_AndSideMenuIsDisplayed() {
        getCategoryName();
        countProductList();
        isMenuDisplayed();
    }
}
