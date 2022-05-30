package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProductOnSalePage extends BasePage{

    private static Logger log = LoggerFactory.getLogger("CategoriesPage.class");

    public ProductOnSalePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "span.discount.discount-percentage")
    private WebElement savePercent;

    @FindBy(css = "span.regular-price")
    private WebElement regularPrice;

    @FindBy(css = "div.product-price.h5.has-discount > div > span:nth-child(1)")
    private WebElement discountPrice;

    @FindBy(css="div:nth-child(2) > h1")
    private WebElement productName;

    public ProductOnSalePage checkVisibilityOfSaveLabel() {
        waitToBeVisible(savePercent);
        waitToBeVisible(regularPrice);
        waitToBeVisible(discountPrice);
        log.info("Selected product: " + getTextFromElement(productName));
        log.info("Save label is visibile. Text inside: " + getTextFromElement(savePercent));
        log.info("Regular price is visibile. Value: " + getTextFromElement(regularPrice));
        log.info("Discount price is visibile. Value: " + getTextFromElement(discountPrice));
        return this;
    }

    public ProductOnSalePage checkCalculateDiscount() {
        double regularPriceValue = Double.parseDouble(regularPrice.getText().substring(1));
        double discountPriceValue = Double.parseDouble(discountPrice.getText().substring(1));
        double priceAfterDiscount = regularPriceValue*(0.8);
        softAssert.assertThat(discountPriceValue == priceAfterDiscount);
        log.info("Discount price is well calculated" + "" +
                " Regular price: " + regularPriceValue + "$" + " after 20% off: " + priceAfterDiscount + "$");
        softAssert.assertThat(discountPriceValue < regularPriceValue);
        log.info("Regular price is higher than the discounted price");
        return this;
    }
}
