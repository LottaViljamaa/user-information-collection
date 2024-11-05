package user.information.collection;

import user.information.collection.userInformation.BasicInformation;
import user.information.collection.userInformation.ContactInformation;
import user.information.collection.userInformation.InformationCollection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static user.information.collection.userInformation.BasicInformation.*;

@SpringBootTest
class BasicInformationTest {

    private InformationService userRepository;

    @BeforeEach
    public void setUp() {
        userRepository = new InformationService();

    }

    @Test
    public void Test_AddUser_InvalidFirstNameFormat() {
        assertThrows(IllegalArgumentException.class, () -> {
            InformationCollection user = new InformationCollection(
                    new BasicInformation("Matti123", "Meikäläinen", "010101", "Suomi", "Mies"),
                    new ContactInformation("010101", "matti.meikalainen@esimerkki.com", "040-123456", "Esimerkkikatu", "Esimerkki", "11111")
            );
            IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
                userRepository.addUser(user);;
            });
            assertEquals(ILLEGAL_NAME_FORMAT, thrown.getMessage());
        });
    }

    @Test
    public void Test_AddUser_InvalidSureNameFormat() {
        assertThrows(IllegalArgumentException.class, () -> {
            InformationCollection user = new InformationCollection(
                    new BasicInformation("Matti", "Meikäläinen123", "010101", "Suomi", "Mies"),
                    new ContactInformation("010101", "matti.meikalainen@esimerkki.com", "040-123456", "Esimerkkikatu", "Esimerkki", "11111")
            );
            IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
                userRepository.addUser(user);;
            });
            assertEquals(ILLEGAL_NAME_FORMAT, thrown.getMessage());
        });
    }

    @Test
    public void Test_AddUser_IllegalPersonalIdentityCodeFormat_ContainLetters() {
        assertThrows(IllegalArgumentException.class, () -> {
            InformationCollection user = new InformationCollection(
                    new BasicInformation("Matti", "Meikäläinen", "abcdef", "Suomi", "Mies"),
                    new ContactInformation("abcdef", "matti.meikalainen@esimerkki.com", "040-123456", "Esimerkkikatu", "Esimerkki", "11111")
            );
            IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
                userRepository.addUser(user);;
            });
            assertEquals(ILLEGAL_ID_FORMAT, thrown.getMessage());
        });
    }

    @Test
    public void Test_AddUser_IllegalPersonalIdentityCodeFormat_IdTooShort() {
        assertThrows(IllegalArgumentException.class, () -> {
            InformationCollection user = new InformationCollection(
                    new BasicInformation("Matti", "Meikäläinen", "01010", "Suomi", "Mies"),
                    new ContactInformation("01010", "matti.meikalainen@esimerkki.com", "040-123456", "Esimerkkikatu", "Esimerkki", "11111")
            );
            IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
                userRepository.addUser(user);;
            });
            assertEquals(ILLEGAL_ID_FORMAT, thrown.getMessage());
        });
    }

    @Test
    public void Test_AddUser_FieldEmpty_FirstName() {
        assertThrows(IllegalArgumentException.class, () -> {
            InformationCollection user = new InformationCollection(
                    new BasicInformation("", "Meikäläinen", "010101", "Suomi", "Mies"),
                    new ContactInformation("010101", "matti.meikalainen@esimerkki.com", "040-123456", "Esimerkkikatu", "Esimerkki", "11111")
            );
            IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
                userRepository.addUser(user);;
            });
            assertEquals(FIELD_EMPTY, thrown.getMessage());
        });
    }

    @Test
    public void Test_AddUser_FieldEmpty_Surname() {
        assertThrows(IllegalArgumentException.class, () -> {
            InformationCollection user = new InformationCollection(
                    new BasicInformation("Matti", "", "010101", "Suomi", "Mies"),
                    new ContactInformation("010101", "matti.meikalainen@esimerkki.com", "040-123456", "Esimerkkikatu", "Esimerkki", "11111")
            );
            IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
                userRepository.addUser(user);;
            });
            assertEquals(FIELD_EMPTY, thrown.getMessage());
        });
    }

    @Test
    public void Test_AddUser_FieldEmpty_PersonalIdentityCode() {
        assertThrows(IllegalArgumentException.class, () -> {
            InformationCollection user = new InformationCollection(
                    new BasicInformation("Matti", "Meikäläinen", "", "Suomi", "Mies"),
                    new ContactInformation("010101", "matti.meikalainen@esimerkki.com", "040-123456", "Esimerkkikatu", "Esimerkki", "11111")
            );
            IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
                userRepository.addUser(user);;
            });
            assertEquals(FIELD_EMPTY, thrown.getMessage());
        });
    }

    @Test
    public void Test_AddUser_FieldEmpty_Citizenship() {
        assertThrows(IllegalArgumentException.class, () -> {
            InformationCollection user = new InformationCollection(
                    new BasicInformation("Matti", "Meikäläinen", "010101", "", "Mies"),
                    new ContactInformation("010101", "matti.meikalainen@esimerkki.com", "040-123456", "Esimerkkikatu", "Esimerkki", "11111")
            );
            IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
                userRepository.addUser(user);;
            });
            assertEquals(FIELD_EMPTY, thrown.getMessage());
        });
    }

    @Test
    public void Test_AddUser_FieldEmpty_Gender() {
        assertThrows(IllegalArgumentException.class, () -> {
            InformationCollection user = new InformationCollection(
                    new BasicInformation("Matti", "Meikäläinen", "010101", "Suomi", ""),
                    new ContactInformation("010101", "matti.meikalainen@esimerkki.com", "040-123456", "Esimerkkikatu", "Esimerkki", "11111")
            );
            IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
                userRepository.addUser(user);;
            });
            assertEquals(FIELD_EMPTY, thrown.getMessage());
        });
    }
}
