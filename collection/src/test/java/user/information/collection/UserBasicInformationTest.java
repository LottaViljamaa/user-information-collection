package user.information.collection;

import user.information.collection.userInformation.UserBasicInformation;
import user.information.collection.userInformation.UserContactInformation;
import user.information.collection.userInformation.UserInformationCollection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static user.information.collection.userInformation.UserBasicInformation.*;

@SpringBootTest
class UserBasicInformationTest {

    private UserInformationService userRepository;

    @BeforeEach
    public void setUp() {
        userRepository = new UserInformationService();

    }

    @Test
    public void Test_AddUser_InvalidFirstNameFormat() {
        assertThrows(IllegalArgumentException.class, () -> {
            UserInformationCollection user = new UserInformationCollection(
                    new UserBasicInformation("Matti123", "Meikäläinen", "010101-1234", "Suomi", "Mies"),
                    new UserContactInformation("010101-1234", "matti.meikalainen@esimerkki.com", "040-123456", "Esimerkkikatu", "Esimerkki", "11111")
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
            UserInformationCollection user = new UserInformationCollection(
                    new UserBasicInformation("Matti", "Meikäläinen123", "010101-1234", "Suomi", "Mies"),
                    new UserContactInformation("010101-1234", "matti.meikalainen@esimerkki.com", "040-123456", "Esimerkkikatu", "Esimerkki", "11111")
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
            UserInformationCollection user = new UserInformationCollection(
                    new UserBasicInformation("Matti", "Meikäläinen", "abcdef-1234", "Suomi", "Mies"),
                    new UserContactInformation("abcdef-1234", "matti.meikalainen@esimerkki.com", "040-123456", "Esimerkkikatu", "Esimerkki", "11111")
            );
            IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
                userRepository.addUser(user);;
            });
            assertEquals(ILLEGAL_ID_FORMAT, thrown.getMessage());
        });
    }

    @Test
    public void Test_AddUser_IllegalPersonalIdentityCodeFormat_NoPunctuationMark() {
        assertThrows(IllegalArgumentException.class, () -> {
            UserInformationCollection user = new UserInformationCollection(
                    new UserBasicInformation("Matti", "Meikäläinen", "01010191234", "Suomi", "Mies"),
                    new UserContactInformation("01010191234", "matti.meikalainen@esimerkki.com", "040-123456", "Esimerkkikatu", "Esimerkki", "11111")
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
            UserInformationCollection user = new UserInformationCollection(
                    new UserBasicInformation("Matti", "Meikäläinen", "010101-123", "Suomi", "Mies"),
                    new UserContactInformation("010101-123", "matti.meikalainen@esimerkki.com", "040-123456", "Esimerkkikatu", "Esimerkki", "11111")
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
            UserInformationCollection user = new UserInformationCollection(
                    new UserBasicInformation("", "Meikäläinen", "010101-1234", "Suomi", "Mies"),
                    new UserContactInformation("010101-1234", "matti.meikalainen@esimerkki.com", "040-123456", "Esimerkkikatu", "Esimerkki", "11111")
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
            UserInformationCollection user = new UserInformationCollection(
                    new UserBasicInformation("Matti", "", "010101-1234", "Suomi", "Mies"),
                    new UserContactInformation("010101-1234", "matti.meikalainen@esimerkki.com", "040-123456", "Esimerkkikatu", "Esimerkki", "11111")
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
            UserInformationCollection user = new UserInformationCollection(
                    new UserBasicInformation("Matti", "Meikäläinen", "", "Suomi", "Mies"),
                    new UserContactInformation("010101-1234", "matti.meikalainen@esimerkki.com", "040-123456", "Esimerkkikatu", "Esimerkki", "11111")
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
            UserInformationCollection user = new UserInformationCollection(
                    new UserBasicInformation("Matti", "Meikäläinen", "010101-1234", "", "Mies"),
                    new UserContactInformation("010101-1234", "matti.meikalainen@esimerkki.com", "040-123456", "Esimerkkikatu", "Esimerkki", "11111")
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
            UserInformationCollection user = new UserInformationCollection(
                    new UserBasicInformation("Matti", "Meikäläinen", "010101-1234", "Suomi", ""),
                    new UserContactInformation("010101-1234", "matti.meikalainen@esimerkki.com", "040-123456", "Esimerkkikatu", "Esimerkki", "11111")
            );
            IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
                userRepository.addUser(user);;
            });
            assertEquals(FIELD_EMPTY, thrown.getMessage());
        });
    }
}
