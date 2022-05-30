package base;

import configuration.AppProperties;
import configuration.browser.BrowserHandler;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestBase {
    protected WebDriver driver;
    private static Logger log = LoggerFactory.getLogger("BaseTest.class");
    protected static BrowserHandler browserHandler;
    private static AppProperties appProperties;

    @BeforeAll
    static void beforeAll() {
        appProperties = AppProperties.getInstance();
        log.info("<<<<<Environment set up properly>>>");
    }

    @BeforeEach
    void beforeEach() {
        browserHandler = new BrowserHandler();
        driver = browserHandler.getDriver();
        log.info("<<<<<Driver initialized>>>");
        driver.get(System.getProperty("appUrl"));
    }

    @AfterEach
    void quit() {
        driver.quit();
        log.info("<<<<<<driver closed properly>>>>>");
    }
}
