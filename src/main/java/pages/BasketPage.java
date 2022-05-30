package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class BasketPage extends BasePage {

    private static Logger log = LoggerFactory.getLogger("BasketPage.class");

    public BasketPage(WebDriver driver) {

        super(driver);
    }

    @FindBy(css = "#cart-subtotal-products > span.value")
    private static WebElement itemsValue;

    @FindBy(css = "span > strong")
    private List<WebElement> price;

    @FindBy(xpath = "//button[contains(@class, 'touchspin-up')]")
    private List<WebElement> productUpBtn;

    @FindBy(xpath = "//button[contains(@class, 'touchspin-down')]")
    private List<WebElement> productDownBtn;

    @FindBy(css = "div.checkout.cart-detailed-actions.card-block > div > a")
    private WebElement proceedBtn;

    @FindBy(xpath = "//a[@class='remove-from-cart']")
    private List<WebElement> trashBtn;

    public String checkTotalCost() {
        return getTextFromElement(itemsValue).replace("$", "");
    }

    public BasketPage checkCostAfterChange() {
        List<String> priceList = new ArrayList<>();

        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        double orderCostAfterChange = 0;

        for (int i = 0; i < price.size(); i++) {
            String value = getTextFromElement(price.get(i)).replace("$", "");
            priceList.add(value);
        }
        for (int i = 0; i < priceList.size(); i++) {
            orderCostAfterChange += Double.parseDouble((priceList.get(i)));
        }
        log.info("Order cost after change : " + String.format("%.2f", orderCostAfterChange).replace(",", "."));
        assert (String.format("%.2f", orderCostAfterChange).replace(",", ".").contains(checkTotalCost()));
        log.info("Order cost is the same");
        return this;
    }

    public BasketPage clickUpArrowRandomProduct() {
        clickOnElement(productUpBtn.get(productUpBtn.size() - 1));
        log.info("Product up button has been clicked");
        return this;
    }

    public BasketPage clickDownArrowRandomProduct() {
        clickOnElement(productDownBtn.get(random.nextInt(productDownBtn.size() - 1)));
        log.info("Product down button has been clicked");
        return this;
    }

    public BasketPage clickTrashButton() {
        for (WebElement element : trashBtn) {
            element = trashBtn.get(0);
            log.info("Size " + trashBtn.size());
            clickOnElement(element);
            log.info("Trash btn has been pressed");
            checkCostAfterChange();
        }
        return this;
    }
}
