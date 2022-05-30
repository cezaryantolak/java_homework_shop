package configuration.models;

import org.openqa.selenium.WebDriver;
import pages.BasePage;

public class UserFactory extends BasePage {

    public UserFactory(WebDriver driver) {
        super(driver);
    }

    public User getRandomUser() {
        User randomUser = new UserBuilder()
                .firstName(getRandomFirstName())
                .lastName(getRandomLastName())
                .email(getRandomEmail())
                .password(getRandomPassword())
                .birthDate(getRandomBirthDate())
                .build();
        return randomUser;
    }
}
