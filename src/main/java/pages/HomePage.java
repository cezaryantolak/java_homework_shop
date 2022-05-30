package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends BasePage {

    private static Logger log = LoggerFactory.getLogger("SearchPage.class");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "div.product-description > h3")
    private List<WebElement> listOfProductsNames;

    @FindBy(css = ".category a")
    private List<WebElement> listOfCategoriesAndSubcategories;

//    private List<String> listOfAllCategoriesAndSubcategories = new ArrayList<>();

    public String randomProductNameFromHomePage() {

        return getRandomElement(listOfProductsNames).getText();
    }
}
