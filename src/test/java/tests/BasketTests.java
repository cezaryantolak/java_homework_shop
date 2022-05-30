package tests;

import base.Pages;
import configuration.models.Product;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class BasketTests extends Pages {

    private static Logger log = LoggerFactory.getLogger("BasketTests.class");

    @Test
    public void shouldAddRandomProduct() {
        for (int i = 0; i < randomProductPage.numberOfAdditionToCart; i++) {
            headerPage
                    .goToRandomCategory();
            randomProductPage
                    .clickRandomProduct()
                    .setRandomQuantityValue()
                    .clickAddToCartButton();
            productOrderDetailsPage
                    .clickContinueShopping()
                    .checkCartOfProducts();

        }
    }

    @Test
    public void shouldAddToBasketCorrectNumberOfProductsAndRemoveThemFromBasket() {

        for (int i = 0; i < randomProductPage.numberOfRandomProduct; i++) {
            headerPage
                    .goToRandomCategory();
            randomProductPage
                    .clickRandomProduct()
                    .clickAddToCartButton();
            productOrderDetailsPage
                    .clickContinueShopping()
                    .checkCartOfProducts();
        }
        randomProductPage
                .clickBasketButton();
        basketPage
                .checkTotalCost();
        assertThat ((String.valueOf(Product.getAllOrderCost())).contains(basketPage.checkTotalCost()));

        randomProductPage
                .setFirstProductQuantity();
        basketPage
                .checkCostAfterChange()
                .clickUpArrowRandomProduct()
                .checkCostAfterChange()
                .clickDownArrowRandomProduct()
                .checkCostAfterChange()
                .clickTrashButton();
    }
}
