package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class HeaderPage extends BasePage {

    private static Logger log = LoggerFactory.getLogger("HeaderPage.class");

    public HeaderPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[@class='dropdown-item']")
    public List<WebElement> categories;

    @FindBy(css = ".category-top-menu>li>a")
    private WebElement categoryName;

    @FindBy(css = ".category-sub-menu a")
    private List<WebElement> subCategories;

    @FindBy(css = "#search_filters")
    private WebElement sideMenu;

    @FindBy(css = "input.ui-autocomplete-input")
    private WebElement searchWindow;

    @FindBy(css = "form > button")
    private WebElement searchButton;

    @FindBy(css = ".account .hidden-sm-down")
    private WebElement signName;

    @FindBy(css = ".user-info .material-icons")
    private WebElement signInButton;

    public HeaderPage clickSignInButton() {
        clickOnElement(signInButton);
        return this;
    }

    public WebElement getSearchButton() {

        return searchButton;
    }

    public WebElement getSearchWindow() {

        return searchWindow;
    }

    public List<WebElement> getCategories() {

        return categories;
    }

    public String getUserSignedName() {

        return getTextFromElement(signName);
    }

    public WebElement getSideMenu() {

        return sideMenu;
    }

    public HeaderPage goToRandomCategory() {
        clickOnElement(getRandomElement(categories));
        return this;
    }
    public int getMainCategoriesSize() {

        return categories.size();
    }

    public void goToCategoryPage(int categoryIndex) {

        clickOnElement(categories.get(categoryIndex));
    }

    public String getCategoryName() {

        return getTextFromElement(categoryName);
    }

    public void goToMainCategory(int categoryIndex) {

        clickOnElement(categories.get(categoryIndex));
    }

    public void goToSubcategory(int index) {

        clickOnElement(subCategories.get(index));
    }

    public int getSubCategoriesListSize() {
        log.info("ListSize: " + subCategories.size());
        if (subCategories.isEmpty()) {
            log.info("There is no subcategory for category: " + getCategoryName());
        }
        return subCategories.size();
    }

    public String getSubCategoryName(int i) {

        return getTextFromElement(subCategories.get(i));
    }


}
