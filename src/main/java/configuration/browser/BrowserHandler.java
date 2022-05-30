package configuration.browser;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BrowserHandler {
    private static Logger log = LoggerFactory.getLogger("BrowserHandler.class");
    private WebDriver driver;
    private String browserName;

    public WebDriver getDriver() {
        this.browserName = System.getProperty("browserName");
        log.info("Chosen browser name: " + browserName);
        log.info("Used driver: " + driver);
        switch (this.browserName) {
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                WebDriverManager.chromedriver().setup();
                chromeOptions.addArguments("start-maximized");
                driver = new ChromeDriver(chromeOptions);
                break;
            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                WebDriverManager.firefoxdriver().setup();
                firefoxOptions.addArguments("start-maximized");
                driver = new FirefoxDriver(firefoxOptions);
                break;
            default:
                EdgeOptions optionsEdge = new EdgeOptions();
                WebDriverManager.edgedriver().setup();
                optionsEdge.addArguments("start-maximized");
                driver = new EdgeDriver(optionsEdge);
        }
        return this.driver;
    }
}
