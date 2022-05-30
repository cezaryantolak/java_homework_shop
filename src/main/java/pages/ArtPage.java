package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class ArtPage extends BasePage {

    private static Logger log = LoggerFactory.getLogger("ArtPage.class");
    HeaderPage headerPage = new HeaderPage(driver);

    public ArtPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "a.ui-slider-handle:nth-of-type(1)")
    private WebElement leftSlider;

    @FindBy(css = "a.ui-slider-handle:nth-of-type(2)")
    private WebElement rightSlider;

    @FindBy(css = ".faceted-slider p")
    private WebElement priceRange;

    @FindBy(xpath = "//div[@id='_desktop_search_filters_clear_all']/button")
    private WebElement clearButton;

    public List<WebElement> getDisplayedProductPrice() {
        return driver.findElements(By.xpath("//span[@class='price']"));
    }

    public ArtPage clickArtCategoryButton() {
        clickOnElement(headerPage.getCategories().get(2));
        log.info("Chosen category is: " + headerPage.getCategories().get(2).getText());
        return this;
    }

    public ArtPage firstPriceFilter(String properties, int offset) {
        int productAmount = getDisplayedProductPrice().size();
        while (!priceRange.getText().endsWith(properties)) {
            waitForPageLoaded();
            clickAndHoldElement(rightSlider);
            moveElementByOffset(offset, 0);
        }
        releaseMouse(rightSlider);
        wait.until(c->getDisplayedProductPrice().size()<productAmount);
        return this;
    }

    public ArtPage secondPriceFilter(String properties, int offset) {
        int productAmount = getDisplayedProductPrice().size();
        while (!priceRange.getText().endsWith(properties)) {
            waitForPageLoaded();
            clickAndHoldElement(leftSlider);
            moveElementByOffset(offset, 0);
        }
        releaseMouse(leftSlider);
        wait.until(c->getDisplayedProductPrice().size()<productAmount);
        return this;
    }



    public ArtPage countMatchedProducts(String properties) {
        List<String> products = new ArrayList<>();
        for (int i = 0; i < getDisplayedProductPrice().size(); i++) {
            waitForPageLoaded();
            waitToBeVisible(getDisplayedProductPrice().get(i));
            products.add(getDisplayedProductPrice().get(i).getText());
            String value = getDisplayedProductPrice().get(i).getText();
            softAssert.assertThat(value.contains(properties));
        }
        log.info("Number of matched products is: " + getDisplayedProductPrice().size());
        return this;
    }

    public ArtPage clearFilters() {
        int productAmount = getDisplayedProductPrice().size();
        clickOnElement(clearButton);
        waitForPageLoaded();
        wait.until(c -> getDisplayedProductPrice().size()>productAmount);
        return this;
    }
}
