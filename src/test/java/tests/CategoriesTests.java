package tests;

import base.Pages;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

public class CategoriesTests extends Pages {

    @Test
    public void shouldDisplayEveryCategoryPage() {
        int categoriesAmount = headerPage.getMainCategoriesSize();
        SoftAssertions softAssertions = new SoftAssertions();
        for (int i = 0; i < categoriesAmount; i++) {
            headerPage.goToCategoryPage(i);
            softAssertions.assertThat(headerPage.getCategoryName().equals(categoriesPage.getCategoryName()));
            softAssertions.assertThat(categoriesPage.checkIfFilerMenuIsDisplayed()).isTrue();
            softAssertions.assertThat(categoriesPage.getLabelWithNumberOfProductsTextList().contains(String.valueOf(categoriesPage.countNumberOfProductsInCategory())));
        }
        softAssertions.assertAll();
    }

    @Test
    public void shouldDisplayEverySubcategoryPage() {
        int categoriesAmount = headerPage.getMainCategoriesSize();
        SoftAssertions softAssertions = new SoftAssertions();
        for (int j = 0; j < categoriesAmount; j++) {
            headerPage.goToMainCategory(j);
            int subCategoriesAmount = headerPage.getSubCategoriesListSize();
            for (int i = 0; i < subCategoriesAmount; i++) {
                String subCategoryName = headerPage.getSubCategoryName(i);
                headerPage.goToSubcategory(i);
                softAssertions.assertThat(subCategoryName.equals(categoriesPage.getCategoryName()));
                softAssertions.assertThat(categoriesPage.checkIfFilerMenuIsDisplayed()).isTrue();
                softAssertions.assertThat(categoriesPage.getLabelWithNumberOfProductsTextList().contains(String.valueOf(categoriesPage.countNumberOfProductsInCategory())));
                driver.navigate().back();
            }
        }
        softAssertions.assertAll();
    }
}
