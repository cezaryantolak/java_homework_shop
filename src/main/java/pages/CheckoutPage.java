package pages;

import configuration.models.Product;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class CheckoutPage extends BasePage {

    private static Logger log = LoggerFactory.getLogger("CheckoutPage.class");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }



    @FindBy(css = "[name=submitCreate]+[name=continue]")
    private WebElement continueRegisterButton;

    @FindBy(css = "[name=address1]")
    private WebElement address;

    @FindBy(css = "[name=address2]")
    private WebElement addressComplement;

    @FindBy(css = "id_state")
    private WebElement selectState;

    @FindBy(css = "[name=id_country]")
    private WebElement selectCountry;

    @FindBy(css = "[name=postcode]")
    private WebElement postalCode;

    @FindBy(css = "[name=city]")
    private WebElement city;

    @FindBy(css = "#use_same_address")
    private WebElement checkSameAddressForInvoice;

    @FindBy(css = "#checkout-addresses-step .step-edit")
    private WebElement editAddress;

    @FindBy(css = ".address")
    private WebElement chosenAddress;

    @FindBy(css = "[name=confirm-addresses]")
    private WebElement confirmAddressButton;

    @FindBy(css = ".cart-summary-products a")
    private WebElement cartSummaryCollapse;

    @FindBy(css = ".media-body")
    private List<WebElement> productsSummaryList;

    @FindBy(css = ".delivery-option")
    private List<WebElement> deliveryOptionRow;

    @FindBy(css = ".delivery-options .custom-radio")
    private List<WebElement> deliveryOption;

    @FindBy(css = "[name=confirmDeliveryOption]")
    private WebElement confirmShippingButton;

    @FindBy(css = "#payment-option-2")
    private WebElement paymentBankWire;

    @FindBy(css = ".payment-option")
    private List<WebElement> paymentOptionsRow;

    @FindBy(css = ".payment-options .custom-radio")
    private List<WebElement> paymentOptions;

    @FindBy(css = "#cta-terms-and-conditions-0")
    private WebElement termsOfConditionLink;

    @FindBy(css = ".modal-content>button.close")
    private WebElement closeAlertButton;

    @FindBy(css = ".custom-checkbox")
    private WebElement checkTermsOFService;

    @FindBy(css = ".js-modal-content p")
    private List<WebElement> termsRuleList;

    @FindBy(css = "#payment-confirmation button")
    private WebElement paymentConfirmationButton;




    public CheckoutPage fillAddressForm() {
        sendKeysToElement(address, getRandomAddress());
        sendKeysToElement(city, getRandomCity());
        sendKeysToElement(postalCode, getRandomPostalCode());
        checkInvoiceAddressBox();
        new Select(selectCountry).selectByValue("14");
        hoverToElement(confirmAddressButton);
        clickOnElement(confirmAddressButton);
        return this;
    }

    public String selectShippingMethod() {
        WebElement random = getRandomElement(deliveryOptionRow);
        WebElement randomRadio = random.findElement(By.cssSelector(".custom-radio"));
        String shippingMethod = getTextFromElement(random.findElement(By.cssSelector(".carrier-name")));
        log.info("Selected shipping method: " + shippingMethod);
        clickOnElement(randomRadio);
        return shippingMethod;
    }

    public String selectPaymentMethod() {
        WebElement payment = paymentOptionsRow.get(1);
        WebElement paymentRadio = payment.findElement(By.cssSelector(".custom-radio"));
        String paymentMethod = payment.findElement(By.cssSelector("label span")).getText();
        paymentMethod = paymentMethod.replace("Pay by ", "");
        log.info("Selected payment method: " + paymentMethod);
        clickOnElement(paymentRadio);
        clickOnElement(termsOfConditionLink);
        driver.switchTo().defaultContent();
        return paymentMethod;
    }


    public CheckoutPage confirmShipping() {
        clickOnElement(confirmShippingButton);
        return this;
    }


    public CheckoutPage acceptTerms() {
        clickOnElement(closeAlertButton);
        boolean isSelected = checkTermsOFService.isSelected();
        if (isSelected == false) {
            clickOnElement(checkTermsOFService);
        }
        log.info("checkbox selected");
        return this;
    }

    public CheckoutPage confirmPayment() {
        clickOnElement(paymentConfirmationButton);
        return this;
    }

    public String getAddress() {
        clickOnElement(editAddress);
        String address = getTextFromElement(chosenAddress);
        clickOnElement(confirmAddressButton);
        log.info("Chosen address: " + address);
        return address;
    }


    public boolean hasAllTermsOfServiceText() {
        List<String> ruleTextList = new ArrayList<>();
        boolean hasText = false;
        for (WebElement termsRule : termsRuleList) {
            if (!getTextFromElement(termsRule).isEmpty()) {
                ruleTextList.add(getTextFromElement(termsRule));
            } else {
                log.info("Rule text missing");
            }
        }
        if (ruleTextList.size() == termsRuleList.size()) {
            hasText = true;
        }
        return hasText;
    }

    public List<Product> getOrderProducts() {
        clickOnElement(cartSummaryCollapse);
        List<Product> products = new ArrayList<>();
        for (WebElement product : productsSummaryList) {
            products.add(new Product(getTextFromElement(new OrderItemPage(product).getProductName()),
                    Integer.getInteger(getQuantityValueOfProduct(getTextFromElement(new OrderItemPage(product).getProductQuantity()))),
                    Integer.getInteger(getTextFromElement(new OrderItemPage(product).getProductPrice()))));
        }
        log.info("Order product list: " + products);
        return products;
    }
    private void checkInvoiceAddressBox() {
        if (!checkSameAddressForInvoice.isSelected()) {
            clickOnElement(checkSameAddressForInvoice);
        }
    }
}

