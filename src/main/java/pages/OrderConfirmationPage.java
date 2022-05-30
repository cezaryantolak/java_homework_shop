package pages;

import configuration.models.OrderBuilder;
import configuration.models.Product;
import configuration.models.Order;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class OrderConfirmationPage extends BasePage {

    private static Logger log = LoggerFactory.getLogger("OrderConfirmationPage.class");

    public OrderConfirmationPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".order-line")
    private List<WebElement> orderProductsList;

    @FindBy(css = "#order-details li")
    private List<WebElement> orderDetails;

    @FindBy(css = "[title=Orders]")
    private WebElement orders;

    @FindBy(xpath = "//tr[1]/td[2]")
    private WebElement subtotalPrice;

    @FindBy(xpath = "//tr[3]/td[2]")
    private WebElement totalOrderValue;

    @FindBy(xpath = "//tr[2]/td[2]")
    private WebElement shippingPrice;

    public void goToOrderHistory() {

        clickOnElement(orders);
    }

    public List<Product> getOrderProducts() {
        List<Product> products = new ArrayList<>();
        for (WebElement product : orderProductsList) {
            products.add(new Product((new OrderConfirmationItemPage(product).getProductName()),
                    Integer.getInteger(getQuantityValueOfProduct(getTextFromElement(new OrderConfirmationItemPage(product).getProductQuantity()))),
                    Integer.getInteger(getTextFromElement(new OrderConfirmationItemPage(product).getProductPrice()))));
        }
        return products;
    }

    public Order getOrderDetails(List<Product> products) {
        Order order = new OrderBuilder()
                .orderReference(getOrderNumber())
                .products(products)
                .totalCost(getTotalCost())
                .paymentMethod(getPaymentMethod())
                .shippingMethod(getShippingMethod())
                .shippingCost(getShippingCost()).build();
        log.info("Order: " + order.toString());
        return order;
    }

    public String getOrderNumber() {
        String orderNumber = getTextFromElement(orderDetails.get(0));
        log.info("number before: " + orderNumber);
        String orderNumber2 = orderNumber.replaceAll(".*: ", "");
        log.info("after " + orderNumber2);
        return orderNumber2;
    }

    public String getPaymentMethod() {

        return getTextFromElement(orderDetails.get(1));//substring
    }

    public String getShippingMethod() {

        return getTextFromElement(orderDetails.get(2));//substring
    }

    private String getShippingCost() {
        String shippingCost = getTextFromElement(shippingPrice);
        return shippingCost;
    }

    private String getTotalCost() {
        String totalCost = getTextFromElement(totalOrderValue);
        return totalCost;
    }

    private String getSubtotalCost() {
        String subtotal = getTextFromElement(subtotalPrice);
        return subtotal;
    }
}
