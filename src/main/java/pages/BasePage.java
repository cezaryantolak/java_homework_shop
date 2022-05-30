package pages;

import com.github.javafaker.Faker;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class BasePage {

    private static Logger log = LoggerFactory.getLogger("BasePage.class");
    public WebDriver driver;
    public WebDriverWait wait;
    private Actions actions;
    protected static Random random = new Random();
    protected SoftAssertions softAssert;
    static Faker faker = new Faker();
    static String datePattern = "MM/dd/yyyy";
    static SimpleDateFormat format = new SimpleDateFormat(datePattern);

    public BasePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(Long.parseLong(System.getProperty("waitTime"))));
        actions = new Actions(driver);
        softAssert = new SoftAssertions();
    }

    public void waitForPageLoaded() {
        wait.until(driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
    }

    public void clickOnElement(WebElement element) {
        waitToBeClickable(element);
        log.info("Clicking on: " + element.getText());
        element.click();
    }

    public void waitToBeVisible(WebElement element) {

        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitToListBeVisible(List<WebElement> elements) {

        wait.until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    public void waitToBeClickable(WebElement element) {

        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void clickAndHoldElement(WebElement element) {
        waitToBeClickable(element);
        actions.clickAndHold(element);
    }

    public void releaseMouse(WebElement element) {
        actions.release(element).perform();
    }

    public void moveElementByOffset(int xOffset, int yOffset) {
        actions.moveByOffset(xOffset, yOffset).perform();
    }

    public WebElement findElementInAnotherElement(WebElement baseElement, String elementToFind) {

        return baseElement.findElement(By.cssSelector(elementToFind));
    }

    public List<WebElement> findElementsInAnotherElement(WebElement baseElement, String elementToFind) {

        return baseElement.findElements(By.cssSelector(elementToFind));
    }

    public String getTextFromElement(WebElement element) {
        waitToBeVisible(element);
        return element.getText();
    }

    public String getAttributeValue(WebElement element, String attribute) {

        return element.getAttribute(attribute);
    }

    public void switchToIframe(WebElement element) {

        driver.switchTo().frame(element);
    }

    public void sendKeysToElement(WebElement element, String value) {
        waitToBeVisible(element);
        element.clear();
        element.sendKeys(value);
    }

    public void hoverToElement(WebElement element) {
        waitToBeVisible(element);
        actions.moveToElement(element).perform();
        log.info("Hover to element: " +element.toString());
    }

    public void dragAndDrop(WebElement elementToDrag, WebElement elementToDrop) {
        actions.dragAndDrop(elementToDrag, elementToDrop).perform();
    }

    public void release() {
        actions.release();
    }

    public void sendKeysCombination(WebElement element, String text) {
        waitToBeVisible(element);
        clickOnElement(element);
        sendKeysToElement(element, Keys.chord(Keys.CONTROL, "a"));
        element.sendKeys(text);
        element.sendKeys(Keys.ENTER);
    }

    public String getQuantityValueOfProduct(String quantity) {
        quantity = quantity.replace("x", "");
        return quantity;
    }

    public double getDoubleValueFromPrice(String price) {
        price = price.replace("$", "");
        double priceValue = Double.parseDouble(price);
        return priceValue;
    }

    public String getRandomNumberValue(int bound) {
        String random = String.valueOf(new Random().nextInt(bound - 1) + 1);
        return random;
    }

    public WebElement getRandomElement(List<WebElement> list) {

        return list.get(new Random().nextInt(list.size()));
    }

    public void clickRandomElement(List<WebElement> list) {
        getRandomElement(list).click();
    }

    public String getRandomFirstName() {
        return faker.name().firstName();
    }

    public String getRandomLastName() {
        return faker.name().lastName();
    }

    public String getRandomEmail() {
        return faker.internet().emailAddress();
    }

    public String getRandomPassword() {
        return faker.internet().password(8, 16);
    }

    public String getRandomBirthDate() {
        String birthday = format.format(faker.date().birthday(8, 16));
        log.info(birthday);
        return birthday;
    }

    public String getRandomAddress() {
        return faker.address().streetName() + " " + faker.address().streetAddressNumber();
    }

    public String getRandomCity() {
        return faker.address().cityName();
    }

    public String getRandomPostalCode() {
        return faker.numerify("##-###");
    }

    public String getTodayDate() {
        String todayDate = format.format(new Date(System.currentTimeMillis()));
        log.info("Today date is: " + todayDate);
        return todayDate;
    }
}
