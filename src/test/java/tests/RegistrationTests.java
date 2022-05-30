package tests;

import base.Pages;
import configuration.models.UserFactory;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class RegistrationTests extends Pages {

    @Test
    public void shouldRegisterNewUser() {
        headerPage
                .clickSignInButton();
        loginPage
                .goToRegistrationForm();
        authenticationAndCreatePage
                .fillRegisterForm(new UserFactory(driver).getRandomUser());

        String registeredUserData = authenticationAndCreatePage.getUserData();

        authenticationAndCreatePage
                .submitRegistrationForm();

        assertThat(registeredUserData, equalTo(headerPage.getUserSignedName()));
    }
}
