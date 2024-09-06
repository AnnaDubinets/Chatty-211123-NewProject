package tests;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;

public class UpdateUserTest {
    Faker faker = new Faker();

    @Test
    public void updateEmail() {
        //4. Change Email

        //1. Create new user

        String userEmail = faker.internet().emailAddress();
        String userFirstName = faker.name().firstName();
        String userLastName = faker.name().lastName();
        String gender = "male";
        String tittle = "mr";
        String phone = "+49123456";


    }
}
