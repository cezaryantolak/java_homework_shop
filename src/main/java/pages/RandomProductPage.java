package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

public class RandomProductPage extends BasePage {

    private static Logger log = LoggerFactory.getLogger("RandomProductPage.class");

    public final int numberOfAdditionToCart = Integer.parseInt(System.getProperty("number_of_addition_to_cart"));
    public final int firstProductQuantity = Integer.parseInt(System.getProperty("first_product_quantity"));
    public final int numberOfRandomProduct = Integer.parseInt(System.getProperty("number_of_random_add_random_product"));

    CategoryPage categoryPage = new CategoryPage(driver);

    public RandomProductPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "#quantity_wanted")
    private WebElement quantity;

    @FindBy(css = "div.add")
    private WebElement addToCartButton;

    @FindBy(css = "#_desktop_cart")
    private WebElement basket;

    @FindBy(css = " div:nth-child(2) > h1")
    private WebElement selectedProductName;

    @FindBy(xpath = "//input[@name='product-quantity-spin']")
    private WebElement firstProduct;

    @FindBy(css = "h1")
    private WebElement bar;

    @FindBy(css = ".product-message")
    private WebElement customizationText;

    @FindBy(xpath = "//button[@name='submitCustomizedData']")
    private WebElement customizationSubmitBtn;

    public RandomProductPage clickRandomProduct() {
        waitForPageLoaded();
        WebElement product = getRandomElement(categoryPage.productList);
        clickOnElement(product);
        log.info("Selected product: " + getTextFromElement(selectedProductName));
        return this;
    }

    public RandomProductPage setRandomQuantityValue() {
        Random random = new Random();
        int value = random.nextInt(3) + 1;
        log.info("quantity: " + value);
        sendKeysToElement(quantity, String.valueOf(value));
        return this;
    }

    public RandomProductPage setFirstProductQuantity() {
        clickOnElement(firstProduct);
        firstProduct.sendKeys(Keys.BACK_SPACE);
        sendKeysToElement(firstProduct, String.valueOf(firstProductQuantity));
        clickOnElement(bar);
        return this;
    }

    public RandomProductPage clickAddToCartButton() {
        if (!addToCartButton.isEnabled()) {
            customizationText.click();
            sendKeysToElement(customizationText, getRandomEmail());
            clickOnElement(customizationSubmitBtn);
            clickOnElement(addToCartButton);
        } else {
            clickOnElement(addToCartButton);
        }
        return this;
    }

    public RandomProductPage clickBasketButton() {
        clickOnElement(basket);
        return this;
    }
}
