package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProductPage extends BasePage {

    private static Logger log = LoggerFactory.getLogger("ProductPage.class");

    public ProductPage(WebDriver driver) {

        super(driver);
    }

    @FindBy(css = "h1[itemprop=name]")
    private WebElement productTitle;

    @FindBy(css = ".current-price span")
    private WebElement currentPrice;

    @FindBy(css = "#quantity_wanted")
    private WebElement quantityInput;

    @FindBy(css = ".add-to-cart")
    private WebElement addToCartButton;

    @FindBy(css = ".btn-secondary")
    private WebElement continueShoppingButton;

    @FindBy(css = "a.btn-primary")
    private WebElement proceedToCheckoutButton;

    @FindBy(css = ".shopping-cart")
    private WebElement cartButton;

    @FindBy(css = "#myModalLabel i")
    private WebElement popUpTitle;

    @FindBy(css = ".product-name")
    private WebElement popUpProductName;

    @FindBy(css = "p.product-price")
    private WebElement popUpProductPrice;

    @FindBy(css = ".product-quantity strong")
    private WebElement popUpProductQuantity;

    @FindBy(css = "p.cart-products-count")
    private WebElement labelWithCartQuantity;

    @FindBy(css = ".product-total .value")
    private WebElement totalValue;

    @FindBy(css = ".product-message")
    private WebElement customProductText;

    @FindBy(css = "[name=submitCustomizedData]")
    private WebElement saveCustomizationButton;

    @FindBy(css = "span.cart-products-count")
    private WebElement cartIcon;

    public String setRandomQuantity(int maxQuantity) {
        String randomQuantity = getRandomNumberValue(maxQuantity);
        if (productTitle.getText().contains("CUSTOMIZABLE")) {
            setCustomText();
        }
        hoverToElement(quantityInput);
        sendKeysToElement(quantityInput, randomQuantity);
        return randomQuantity;
    }

    public ProductPage addToCart() {
        hoverToElement(addToCartButton);
        clickOnElement(addToCartButton);
        return this;
    }

    public void proceedToCheckout() {

        clickOnElement(proceedToCheckoutButton);
    }

    public void continueShopping() {

        clickOnElement(continueShoppingButton);
    }


    private void setCustomText() {
        sendKeysToElement(customProductText, getRandomFirstName());
        clickOnElement(saveCustomizationButton);
    }
}
