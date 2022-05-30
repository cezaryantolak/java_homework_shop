package tests;

import base.Pages;
import configuration.models.Product;
import configuration.models.Order;
import configuration.models.UserFactory;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class CheckoutTests extends Pages {

    List<Product> cartProducts = new ArrayList<>();
    List<Product> orderProducts = new ArrayList<>();
    List<Product> orderConfirmationProducts = new ArrayList<>();
    List<Product> lastOrderProducts = new ArrayList<>();
    Order orderConfirmed;
    Order historyOrder;
    SoftAssertions softAssertions = new SoftAssertions();


    @BeforeEach
    public void registerBeforeTests() {
        headerPage.clickSignInButton();
        loginPage.goToRegistrationForm();
        authenticationAndCreatePage.fillRegisterForm(new UserFactory(driver).getRandomUser());
        authenticationAndCreatePage.submitRegistrationForm();
    }

    @Test
    public void shouldGoToOrderCheckoutWithSuccess() {
        addRandomProductsToCart(Integer.parseInt(System.getProperty("number_of_random_add_random_product")));
        cartProducts = cartPage.getCartProducts();
        productPage
                .proceedToCheckout();
        checkoutPage
                .fillAddressForm();

        String chosenAddress = checkoutPage.getAddress();
        orderProducts = checkoutPage.getOrderProducts();
        String chosenShippingMethod = checkoutPage.selectShippingMethod();
        checkoutPage
                .confirmShipping();

        String chosenPaymentMethod = checkoutPage.selectPaymentMethod();
        softAssertions.assertThat(checkoutPage.hasAllTermsOfServiceText()).isTrue();
        checkoutPage
                .acceptTerms().confirmPayment();

        orderConfirmed = orderConfirmationPage.getOrderDetails(orderConfirmationProducts = orderConfirmationPage.getOrderProducts());//do assertion, add date
        orderConfirmationPage
                .goToOrderHistory();

        String orderReference = orderConfirmed.getOrderReference();
        lastOrderProducts = orderHistoryPage.getChosenOrderProducts(orderHistoryPage.searchOrder(orderReference));
        String invoiceAddress = orderHistoryPage.getInvoiceAddress();
        String deliveryAddress = orderHistoryPage.getDeliveryAddress();

        orderHistoryPage
                .goBack();

        historyOrder = orderHistoryPage.getChosenOrderDetails(lastOrderProducts, orderHistoryPage.searchOrder(orderReference));

        softAssertions.assertThat(historyOrder.getDate()).isEqualTo(basePage.getTodayDate());
        softAssertions.assertThat(historyOrder.getStatus().contains(chosenPaymentMethod));
        softAssertions.assertThat(orderConfirmed.getPaymentMethod()).containsIgnoringCase("bank");
        softAssertions.assertThat(chosenPaymentMethod).containsIgnoringCase("bank");
        softAssertions.assertThat(orderConfirmed.getShippingMethod().contains(chosenShippingMethod));
        softAssertions.assertThat(chosenAddress).isEqualTo(invoiceAddress);
        softAssertions.assertThat(chosenAddress).isEqualTo(deliveryAddress);
        softAssertions.assertThat(orderConfirmed.getProducts().toString()).isEqualTo(historyOrder.getProducts().toString());
        softAssertions.assertThat(cartProducts.toString()).isEqualTo(orderProducts.toString());
        softAssertions.assertAll();
    }

    public void addRandomProductsToCart(int numberOfProducts) {
        for (int i = 0; i < numberOfProducts; i++) {
            headerPage.goToRandomCategory();
            categoriesPage.goToRandomProduct();
            productPage.setRandomQuantity(5);
            productPage.addToCart();
            if (i < numberOfProducts - 1) {
                productPage.continueShopping();
                driver.get(System.getProperty("appUrl"));
            } else {
                productPage.proceedToCheckout();
            }
        }
    }
}
