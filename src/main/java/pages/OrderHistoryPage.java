package pages;

import configuration.models.Order;

import configuration.models.OrderBuilder;
import configuration.models.Product;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class OrderHistoryPage extends BasePage {

    private static Logger log = LoggerFactory.getLogger("OrderHistory.class");

    public OrderHistoryPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "tbody tr")
    private List<WebElement> orders;

    @FindBy(css = "#order-products tbody tr")
    private List<WebElement> orderHistoryDetailProductList;

    @FindBy(css = "#delivery-address address")
    private WebElement deliveryAddress;

    @FindBy(css = "#invoice-address address")
    private WebElement invoiceAddress;

    public Order getChosenOrderDetails(List<Product> lastOrderProducts, int index) {

        Order chosenOrder = new OrderBuilder()
                .orderReference(new OrderHistoryRowPage(orders.get(index)).getOrderReference())//check
                .products(lastOrderProducts)//check
                .totalCost(new OrderHistoryRowPage(orders.get(index)).getOrderTotalPrice())//check
                .paymentMethod(new OrderHistoryRowPage(orders.get(index)).getPaymentOption())
                .status(new OrderHistoryRowPage(orders.get(index)).getStatus())//check
                .date(new OrderHistoryRowPage(orders.get(index)).getOrderDate())//check
                .build();
        log.info("Chosen order: " + chosenOrder.toString());
        return chosenOrder;
    }

    public void goToChosenOrderProducts(int index) {
        new OrderHistoryRowPage(orders.get(index)).getOrderDetailsLink().click();
    }

    public List<Product> getChosenOrderProducts(int index) {
        goToChosenOrderProducts(index);
        List<Product> products = new ArrayList<>();
        for (WebElement item : orderHistoryDetailProductList) {
            products.add(new Product((new OrderHistoryItemPage(item).getProductName()),
                    Integer.getInteger(getQuantityValueOfProduct(getTextFromElement(new OrderHistoryItemPage(item).getProductQuantity()))),
                    Integer.getInteger(getTextFromElement(new OrderHistoryItemPage(item).getProductPrice()))));
        }

        return products;
    }

    public void goBack() {
        driver.navigate().back();
    }

    public int searchOrder(String orderReference) {
        int index = 0;
        for (WebElement order : orders) {
            if (new OrderHistoryRowPage(order).getOrderReference().equals(orderReference)) {
                index = orders.indexOf(order);
            }
        }

        return index;
    }

    public String getDeliveryAddress() {
        String deliveryAddress = getTextFromElement(this.deliveryAddress);
        log.info("Delivery address: " + deliveryAddress);
        return deliveryAddress;
    }

    public String getInvoiceAddress() {
        String invoiceAddress = getTextFromElement(this.invoiceAddress);
        log.info("Invoice address: " + invoiceAddress);
        return invoiceAddress;
    }
}
