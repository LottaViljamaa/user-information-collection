package user.information.collection;

import user.information.collection.userInformation.BasicInformation;
import user.information.collection.userInformation.ContactInformation;
import user.information.collection.userInformation.InformationCollection;
import org.junit.jupiter.api.Test;

import static user.information.collection.userInformation.BasicInformation.FIELD_EMPTY;
import static user.information.collection.userInformation.ContactInformation.*;
import static user.information.collection.userInformation.ContactInformation.ILLEGAL_POSTAL_CODE;
import static org.junit.jupiter.api.Assertions.*;


public class ContactInformationTest {

    private InformationService userRepository;

    @Test
    public void Test_AddUser_IllegalEmailFormat_DoesNotContainAt() {
        assertThrows(IllegalArgumentException.class, () -> {
            InformationCollection user = new InformationCollection(
                    new BasicInformation("Matti", "Meikäläinen", "010101", "Suomi", "Mies"),
                    new ContactInformation("010101", "matti.meikalainenesimerkki.com", "040-123456", "Esimerkkikatu", "Esimerkki", "11111")
            );
            IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
                userRepository.addUser(user);;
            });
            assertEquals(ILLEGAL_EMAIL_FORMAT, thrown.getMessage());
        });
    }

    @Test
    public void Test_AddUser_IllegalEmailFormat_DoesNotContainDotAfterAt() {
        assertThrows(IllegalArgumentException.class, () -> {
            InformationCollection user = new InformationCollection(
                    new BasicInformation("Matti", "Meikäläinen", "010101", "Suomi", "Mies"),
                    new ContactInformation("010101", "matti.meikalainen@esimerkkicom", "040-123456", "Esimerkkikatu", "Esimerkki", "11111")
            );
            IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
                userRepository.addUser(user);;
            });
            assertEquals(ILLEGAL_EMAIL_FORMAT, thrown.getMessage());
        });
    }

    @Test
    public void Test_AddUser_IllegalPhoneNumber_ContainLetters() {
        assertThrows(IllegalArgumentException.class, () -> {
            InformationCollection user = new InformationCollection(
                    new BasicInformation("Matti", "Meikäläinen", "010101", "Suomi", "Mies"),
                    new ContactInformation("010101", "matti.meikalainen@esimerkki.com", "abc-123456", "Esimerkkikatu", "Esimerkki", "11111")
            );
            IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
                userRepository.addUser(user);;
            });
            assertEquals(ILLEGAL_PHONE_NUMBER, thrown.getMessage());
        });
    }

    @Test
    public void Test_AddUser_IllegalPhoneNumber_NumberTooLong() {
        assertThrows(IllegalArgumentException.class, () -> {
            InformationCollection user = new InformationCollection(
                    new BasicInformation("Matti", "Meikäläinen", "010101", "Suomi", "Mies"),
                    new ContactInformation("010101", "matti.meikalainen@esimerkki.com", "040-123456789123456789", "Esimerkkikatu", "Esimerkki", "11111")
            );
            IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
                userRepository.addUser(user);;
            });
            assertEquals(ILLEGAL_PHONE_NUMBER, thrown.getMessage());
        });
    }

    @Test
    public void Test_AddUser_IllegalPostalCode_ContainLetters() {
        assertThrows(IllegalArgumentException.class, () -> {
            InformationCollection user = new InformationCollection(
                    new BasicInformation("Matti", "Meikäläinen", "010101", "Suomi", "Mies"),
                    new ContactInformation("010101", "matti.meikalainen@esimerkki.com", "040-123456", "Esimerkkikatu", "Esimerkki", "abcde")
            );
            IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
                userRepository.addUser(user);;
            });
            assertEquals(ILLEGAL_POSTAL_CODE, thrown.getMessage());
        });
    }

    @Test
    public void Test_AddUser_IllegalPostalCode_TooShort() {
        assertThrows(IllegalArgumentException.class, () -> {
            InformationCollection user = new InformationCollection(
                    new BasicInformation("Matti", "Meikäläinen", "010101", "Suomi", "Mies"),
                    new ContactInformation("010101", "matti.meikalainen@esimerkki.com", "040-123456", "Esimerkkikatu", "Esimerkki", "1234")
            );
            IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
                userRepository.addUser(user);;
            });
            assertEquals(ILLEGAL_POSTAL_CODE, thrown.getMessage());
        });
    }

    @Test
    public void Test_AddUser_IllegalPostalCode_TooLong() {
        assertThrows(IllegalArgumentException.class, () -> {
            InformationCollection user = new InformationCollection(
                    new BasicInformation("Matti", "Meikäläinen", "010101", "Suomi", "Mies"),
                    new ContactInformation("010101", "matti.meikalainen@esimerkki.com", "040-123456", "Esimerkkikatu", "Esimerkki", "123456")
            );
            IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
                userRepository.addUser(user);;
            });
            assertEquals(ILLEGAL_POSTAL_CODE, thrown.getMessage());
        });
    }

    @Test
    public void Test_AddUser_FieldEmpty_PersonalIdentityCode() {
        assertThrows(IllegalArgumentException.class, () -> {
            InformationCollection user = new InformationCollection(
                    new BasicInformation("Matti", "Meikäläinen", "010101", "Suomi", "Mies"),
                    new ContactInformation("", "matti.meikalainen@esimerkki.com", "040-123456", "Esimerkkikatu", "Esimerkki", "11111")
            );
            IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
                userRepository.addUser(user);;
            });
            assertEquals(FIELD_EMPTY, thrown.getMessage());
        });
    }

    @Test
    public void Test_AddUser_FieldEmpty_Email() {
        assertThrows(IllegalArgumentException.class, () -> {
            InformationCollection user = new InformationCollection(
                    new BasicInformation("Matti", "Meikäläinen", "010101", "Suomi", "Mies"),
                    new ContactInformation("010101", "", "040-123456", "Esimerkkikatu", "Esimerkki", "11111")
            );
            IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
                userRepository.addUser(user);;
            });
            assertEquals(FIELD_EMPTY, thrown.getMessage());
        });
    }

    @Test
    public void Test_AddUser_FieldEmpty_PhoneNumber() {
        assertThrows(IllegalArgumentException.class, () -> {
            InformationCollection user = new InformationCollection(
                    new BasicInformation("Matti", "Meikäläinen", "010101", "Suomi", "Mies"),
                    new ContactInformation("010101", "matti.meikalainen@esimerkki.com", "", "Esimerkkikatu", "Esimerkki", "11111")
            );
            IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
                userRepository.addUser(user);;
            });
            assertEquals(FIELD_EMPTY, thrown.getMessage());
        });
    }

    @Test
    public void Test_AddUser_FieldEmpty_StreetAddress() {
        assertThrows(IllegalArgumentException.class, () -> {
            InformationCollection user = new InformationCollection(
                    new BasicInformation("Matti", "Meikäläinen", "010101", "Suomi", "Mies"),
                    new ContactInformation("010101", "matti.meikalainen@esimerkki.com", "040-123456", "", "Esimerkki", "11111")
            );
            IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
                userRepository.addUser(user);;
            });
            assertEquals(FIELD_EMPTY, thrown.getMessage());
        });
    }

    @Test
    public void Test_AddUser_FieldEmpty_City() {
        assertThrows(IllegalArgumentException.class, () -> {
            InformationCollection user = new InformationCollection(
                    new BasicInformation("Matti", "Meikäläinen", "010101", "Suomi", "Mies"),
                    new ContactInformation("010101", "matti.meikalainen@esimerkki.com", "040-123456", "Esimerkkikatu", "", "11111")
            );
            IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
                userRepository.addUser(user);;
            });
            assertEquals(FIELD_EMPTY, thrown.getMessage());
        });
    }

    @Test
    public void Test_AddUser_FieldEmpty_PostalCode() {
        assertThrows(IllegalArgumentException.class, () -> {
            InformationCollection user = new InformationCollection(
                    new BasicInformation("Matti", "Meikäläinen", "010101", "Suomi", "Mies"),
                    new ContactInformation("010101", "matti.meikalainen@esimerkki.com", "040-123456", "Esimerkkikatu", "Esimerkki", "")
            );
            IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
                userRepository.addUser(user);;
            });
            assertEquals(FIELD_EMPTY, thrown.getMessage());
        });
    }
}
