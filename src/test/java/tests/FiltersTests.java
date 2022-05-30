package tests;

import base.Pages;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FiltersTests extends Pages {

    private static Logger log = LoggerFactory.getLogger("FiltersTests.class");

    @Test
    public void shouldGetListOfProductsWithMatchedPriceFilter() {
        artPage
                .clickArtCategoryButton()
                .firstPriceFilter(System.getProperty("max_first_filter"), Integer.parseInt(System.getProperty("go_left")))
                .countMatchedProducts(System.getProperty("min_first_filter"))
                .clearFilters()
                .secondPriceFilter(System.getProperty("max_second_filter"), Integer.parseInt(System.getProperty("go_right")))
                .countMatchedProducts(System.getProperty("min_second_filter"));
    }
}
