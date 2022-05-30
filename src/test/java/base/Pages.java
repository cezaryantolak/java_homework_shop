package base;

import org.junit.jupiter.api.BeforeEach;
import pages.*;

public class Pages extends TestBase {

    public BasePage basePage;
    public HomePage homePage;
    public SearchPage searchPage;
    public HeaderPage headerPage;
    public ArtPage artPage;
    public BasketPage basketPage;
    public CategoriesPage categoriesPage;
    public RandomProductPage randomProductPage;
    public FooterPage footerPage;
    public PriceDropPage priceDropPage;
    public CartPage cartPage;
    public ProductPage productPage;
    public ProductOnSalePage productOnSalePage;
    public ProductOrderDetailsPage productOrderDetailsPage;
    public LoginPage loginPage;
    public AuthenticationAndCreatePage authenticationAndCreatePage;
    public CheckoutPage checkoutPage;
    public OrderConfirmationPage orderConfirmationPage;
    public OrderHistoryPage orderHistoryPage;



    @BeforeEach
    public void TestSetup() {
        basePage = new BasePage(driver);
        homePage = new HomePage(driver);
        searchPage = new SearchPage(driver);
        headerPage = new HeaderPage(driver);
        artPage = new ArtPage(driver);
        basketPage = new BasketPage(driver);
        categoriesPage = new CategoriesPage(driver);
        randomProductPage = new RandomProductPage(driver);
        footerPage = new FooterPage(driver);
        priceDropPage = new PriceDropPage(driver);
        cartPage = new CartPage(driver);
        productPage = new ProductPage(driver);
        productOnSalePage = new ProductOnSalePage(driver);
        productOrderDetailsPage = new ProductOrderDetailsPage(driver);
        loginPage = new LoginPage(driver);
        authenticationAndCreatePage = new AuthenticationAndCreatePage(driver);
        checkoutPage = new CheckoutPage(driver);
        orderConfirmationPage = new OrderConfirmationPage(driver);
        orderHistoryPage = new OrderHistoryPage(driver);
    }
}
