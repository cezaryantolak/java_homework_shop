package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class CategoriesPage extends BasePage {

    private static Logger log = LoggerFactory.getLogger("CategoriesPage.class");

    public CategoriesPage(WebDriver driver) {

        super(driver);
    }

    @FindBy(css = ".block-category h1")
    private WebElement categoryPageName;
    @FindBy(css = "#search_filters")
    private WebElement filterMenu;
    @FindBy(css = ".faceted-slider")
    private WebElement sliderValues;
    @FindBy(css = ".ui-slider-horizontal  a:last-child")
    private WebElement maxSlider;
    @FindBy(css = ".ui-slider-horizontal  a:nth-last-child(2)")
    private WebElement minSlider;
    @FindBy(css = ".total-products")
    WebElement totalProductsAmount;
    @FindBy(css = ".product")
    private List<WebElement> productMiniature;
    @FindBy(css = ".js-search-filters-clear-all")
    private WebElement clearFilterButton;

    @FindBy(css = "#js-product-list-header")
    private WebElement salePageTitle;


    public String getCategoryName() {
        String name = getTextFromElement(categoryPageName);
        log.info("Current category is: " + name);
        return name;
    }

    public boolean checkIfFilerMenuIsDisplayed() {
        boolean isFilterDisplayed = false;
        if (filterMenu.isDisplayed()) {
            isFilterDisplayed = true;
            log.info("There is filter for price between " + getCurrentFilterValues()[0] + " and " + getCurrentFilterValues()[1]);
            return true;
        } else log.info("There is no filter");
        return isFilterDisplayed;
    }

    public int countNumberOfProductsInCategory() {
        int numberOfProducts = productMiniature.size();
        log.info("Products quantity in category: " + numberOfProducts);
        return numberOfProducts;
    }

    public String getLabelWithNumberOfProductsTextList() {
        String label = totalProductsAmount.getText();
        log.info((totalProductsAmount.getText()) + " in category");
        return label;
    }

    public String[] getCurrentFilterValues() {
        String[] nums = new String[2];
        String dataValues = sliderValues.getAttribute("data-slider-values");
        if (dataValues.equals("null")) {
            nums[0] = String.valueOf(getMinValue());
            nums[1] = String.valueOf(getMaxValue());
        } else nums = dataValues.replaceAll("[^0-9.]+", " ").trim().split(" ");
        return nums;
    }

    public CategoriesPage goToRandomProduct() {
        getRandomElement(productMiniature).click();
        return this;
    }

    private double getMinValue() {
        return Double.parseDouble(sliderValues.getAttribute("data-slider-min"));
    }

    private double getMaxValue() {
        return Double.parseDouble(sliderValues.getAttribute("data-slider-max"));
    }
}
