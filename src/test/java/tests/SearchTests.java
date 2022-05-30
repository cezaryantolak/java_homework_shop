package tests;

import base.Pages;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

public class SearchTests extends Pages {

    private static Logger log = LoggerFactory.getLogger("SearchTests.class");

    @Test
    public void shouldFindSearchedRandomProductNameOnPage() {
        searchPage
                .fillSearchWindowWithRandomProduct()
                .clickSearchButton();

        log.info("Product name after search is: ", searchPage.getTextFromSearchWindowAfterSearch());
        assertThat(searchPage.getTextFromSearchWindowAfterSearch(), containsString(searchPage.getProduct()));
    }

    @Test
    public void shouldFindProductNameInDropDownResult() {
        searchPage
                .fillSearchWindowWithRandomProduct();

        log.info("Random product name is: " + searchPage.getTextFromSearchWindow());
        assertThat(searchPage.getProductNameFromDropDownList(), containsString(searchPage.getProduct()));
    }
}
