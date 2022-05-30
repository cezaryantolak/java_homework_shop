package pages;

import configuration.models.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static configuration.models.Product.productList;

public class ProductOrderDetailsPage extends BasePage {

    private static Logger log = LoggerFactory.getLogger("ProductOrderDetailsPage.class");
    public Product newProduct;
    private List<Product> basketList = new ArrayList<>();

    public ProductOrderDetailsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".product-container [itemprop=name]")
    private static WebElement productName;

    @FindBy(css = "[itemprop=price]")
    private static WebElement productPrice;

    @FindBy(css = "#quantity_wanted")
    private static WebElement productQuantity;

    @FindBy(css = "div.col-md-7 > div > div > button")
    private WebElement continueShoppingBtn;

    @FindBy(xpath = "//div[@class='cart-content-btn']/a")
    private WebElement proceedBtn;

    @FindBy(css = ".product-line-grid")
    private List<WebElement> productRows;

    public Product newProductBuilder() {
        String productNameText = getTextFromElement(productName);
        String productPriceText = productPrice.getText().replace("$", "");
        String productQuantityText = getAttributeValue(productQuantity, "value");

        Product product = new Product(productNameText, Double.parseDouble(productPriceText),
                Integer.parseInt(productQuantityText));
        log.info("Created item:" + product);
        return product;
    }

    public ProductOrderDetailsPage clickContinueShopping() {
        clickOnElement(continueShoppingBtn);
        return this;
    }

    public void checkCartOfProducts() {
        newProduct = newProductBuilder();


        if (productList.contains(newProduct)) {
            Product productFromList = productList.get(productList.indexOf(newProduct));
            log.info("List contains item");
            productFromList.addQuantity((int) newProduct.getQuantity());
            log.info("Quantity updated " + newProduct.getProductName() + " quantity after updated: " + newProduct.getQuantity());

        } else {
            log.warn("List doesn't contains item");
            productList.add(newProduct);
            log.info("New product added: " + newProduct.getProductName());
        }
        log.info("Products in list: ");
        productList.forEach(product -> log.info("\t- " + product.toString() + "\n"));
    }
}
