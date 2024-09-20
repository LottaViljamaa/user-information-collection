package user.information.collection;

import user.information.collection.userInformation.BasicInformation;
import user.information.collection.userInformation.ContactInformation;
import user.information.collection.userInformation.InformationCollection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static user.information.collection.InformationService.*;
import static org.junit.jupiter.api.Assertions.*;

;

@SpringBootTest
class InformationServiceTest {

	private InformationService userRepository;

	@BeforeEach
	public void setUp() {
		userRepository = new InformationService();

		BasicInformation basicInfo = new BasicInformation("Matti", "Meikäläinen", "010101-1234", "Suomi", "Mies");
		ContactInformation contactInfo = new ContactInformation("010101-1234", "matti.meikalainen@esimerkki.com", "040-123456", "Esimerkkikatu", "Esimerkki", "00000");
		InformationCollection user = new InformationCollection(basicInfo, contactInfo);

		userRepository.addUser(user);
	}

	@Test
	public void Test_AddUser() {
		BasicInformation basicInfo = new BasicInformation("Elli", "Esimerkki", "020202-6789", "Suomi", "Nainen");
		ContactInformation contactInfo = new ContactInformation("020202-6789", "elli.esimerkki@esimerkki.com", "050-987654", "Ellikatu", "Esimerkki", "00500");
		InformationCollection user = new InformationCollection(basicInfo, contactInfo);

		userRepository.addUser(user);
		List<InformationCollection> users = userRepository.getAllUsers();

		assertEquals(2, users.size());
		assertEquals(user, users.get(1));
	}


	@Test
	public void Test_RemoveUser() {
		userRepository.removeUserByPersonalIdentityCode("010101-1234");

		List<InformationCollection> users = userRepository.getAllUsers();
		assertTrue(users.isEmpty());
	}

	@Test
	public void Test_RemoveUser_UserNotFoundByIdentityCode() {
		String nonExistentPersonalIdentityCode = "123456-7890";

		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			userRepository.removeUserByPersonalIdentityCode(nonExistentPersonalIdentityCode);
		});


		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(USER_NOT_FOUND_MESSAGE));
	}

	@Test
	public void Test_UpdateUser() {

		BasicInformation updatedBasicInfo = new BasicInformation("Matti", "Virtanen", "010101-1234", "Suomi", "Mies");
		ContactInformation updatedContactInfo = new ContactInformation("010101-1234", "matti.virtanen@esimerkki.com", "040-987654", "Virtasenkatu", "Tampere", "33100");
		InformationCollection updatedUser = new InformationCollection(updatedBasicInfo, updatedContactInfo);

		userRepository.updateUser("010101-1234", updatedUser);

		InformationCollection fetchedUser = userRepository.getAllUsers().get(0);
		assertEquals("Matti", fetchedUser.getBasicInformation().getFirstName());
		assertEquals("Virtanen", fetchedUser.getBasicInformation().getSurname());
		assertEquals("040-987654", fetchedUser.getContactInformation().getPhoneNumber());
		assertEquals("Virtasenkatu", fetchedUser.getContactInformation().getStreetAddress());
		assertEquals("Tampere", fetchedUser.getContactInformation().getCity());
	}

	@Test
	public void Test_UpdateUser_UserNotFoundByIdentityCode() {

		BasicInformation updatedBasicInfo = new BasicInformation("Matti", "Virtanen", "020202-5678", "Suomi", "Mies");
		ContactInformation updatedContactInfo = new ContactInformation("020202-5678", "matti.virtanen@esimerkki.com", "040-987654", "Virtasenkatu", "Tampere", "33100");
		InformationCollection updatedUser = new InformationCollection(updatedBasicInfo, updatedContactInfo);

		assertThrows(IllegalArgumentException.class, () -> {
			userRepository.updateUser("999999-9999", updatedUser);
		}, USER_NOT_FOUND_MESSAGE);
	}

	@Test
	public void Test_GetUserByPersonalIdentityCode() {

		InformationCollection foundUser = userRepository.getUserByPersonalIdentityCode("010101-1234");

		assertNotNull(foundUser);
		assertEquals("Matti", foundUser.getBasicInformation().getFirstName());
		assertEquals("010101-1234", foundUser.getBasicInformation().getPersonalIdentityCode());

		System.out.println("User found: " + foundUser.getBasicInformation().getFirstName() +
				" " + foundUser.getBasicInformation().getSurname() +
				", Personal Identity Code: " + foundUser.getBasicInformation().getPersonalIdentityCode());
	}

	@Test
	void Test_GetUserByPersonalIdentityCode_UserNotFoundByIdentityCode() {

		String nonExistentCode = "999999-9999";

		IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
			userRepository.getUserByPersonalIdentityCode(nonExistentCode);
		});

		assertEquals(USER_NOT_FOUND_MESSAGE, thrown.getMessage());
	}

	@Test
	public void Test_AddUser_DuplicatePersonalIdentityCode() {
		BasicInformation basicInfo = new BasicInformation("Matti", "Meikäläinen", "010101-1234", "Suomi", "Mies");
		ContactInformation contactInfo = new ContactInformation("010101-1234", "matti.meikalainen@esimerkki.com", "040-123456", "Esimerkkikatu", "Esimerkki", "00000");
		InformationCollection user = new InformationCollection(basicInfo, contactInfo);

		IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
			userRepository.addUser(user);
		});
		assertEquals(InformationService.DUPLICATE_ID_MESSAGE, thrown.getMessage());
	}
}
