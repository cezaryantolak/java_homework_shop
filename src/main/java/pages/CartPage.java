package pages;

import configuration.models.Product;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartPage extends BasePage {

    private static Logger log = LoggerFactory.getLogger("CartPage.class");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".cart-item")
    private List<WebElement> cartItem;
    @FindBy(css = ".cart-total span:last-child")
    private WebElement totalCartPrice;
    @FindBy(css = "#cart-subtotal-shipping span:nth-child(2)")
    private WebElement shippingPrice;
    @FindBy(css = ".cart-summary-line")
    private List<WebElement> shippingDetails;

    public List<Product> getCartProducts() {
        List<Product> cartProducts = new ArrayList<>();
        for (WebElement product : cartItem) {
            cartProducts.add(new Product((new CartItemPage(product).getItemName()),
                    Double.parseDouble(getTextFromElement(new CartItemPage(product).getCurrentPrice()).replace("$", "")),
                    Integer.parseInt(getAttributeValue((new CartItemPage(product).getQuantityInput()), "value"))));

        }
        log.info("Cart product list: " + cartProducts);
        return cartProducts;
    }
}
