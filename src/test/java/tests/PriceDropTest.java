package tests;

import base.Pages;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PriceDropTest extends Pages {

    private static Logger log = LoggerFactory.getLogger("PriceDropTest.class");

    @Test
    public void shouldShowRegularAndDiscountedPrices() {
        footerPage
                .clickPriceDropButton();
        priceDropPage
                .checkOnSalePageAreLoaded()
                .checkVisibilityOfPricesAndDiscount()
                .calculateAndCheckDiscountPrice()
                .openOneProductPage();
        productOnSalePage
                .checkVisibilityOfSaveLabel()
                .checkCalculateDiscount();
    }
}
