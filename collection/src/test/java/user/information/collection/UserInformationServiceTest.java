package user.information.collection;

import user.information.collection.userInformation.UserBasicInformation;
import user.information.collection.userInformation.UserContactInformation;
import user.information.collection.userInformation.UserInformationCollection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static user.information.collection.UserInformationService.*;
import static org.junit.jupiter.api.Assertions.*;

;

@SpringBootTest
class UserInformationServiceTest {

	private UserInformationService userRepository;

	@BeforeEach
	public void setUp() {
		userRepository = new UserInformationService();

		UserBasicInformation basicInfo = new UserBasicInformation("Matti", "Meikäläinen", "010101-1234", "Suomi", "Mies");
		UserContactInformation contactInfo = new UserContactInformation("010101-1234", "matti.meikalainen@esimerkki.com", "040-123456", "Esimerkkikatu", "Esimerkki", "00000");
		UserInformationCollection user = new UserInformationCollection(basicInfo, contactInfo);

		userRepository.addUser(user);
	}

	@Test
	public void Test_AddUser() {
		UserBasicInformation basicInfo = new UserBasicInformation("Elli", "Esimerkki", "020202-6789", "Suomi", "Nainen");
		UserContactInformation contactInfo = new UserContactInformation("020202-6789", "elli.esimerkki@esimerkki.com", "050-987654", "Ellikatu", "Esimerkki", "00500");
		UserInformationCollection user = new UserInformationCollection(basicInfo, contactInfo);

		userRepository.addUser(user);
		List<UserInformationCollection> users = userRepository.getAllUsers();

		assertEquals(2, users.size());
		assertEquals(user, users.get(1));
	}


	@Test
	public void Test_RemoveUser() {
		userRepository.removeUserByPersonalIdentityCode("010101-1234");

		List<UserInformationCollection> users = userRepository.getAllUsers();
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

		UserBasicInformation updatedBasicInfo = new UserBasicInformation("Matti", "Virtanen", "010101-1234", "Suomi", "Mies");
		UserContactInformation updatedContactInfo = new UserContactInformation("010101-1234", "matti.virtanen@esimerkki.com", "040-987654", "Virtasenkatu", "Tampere", "33100");
		UserInformationCollection updatedUser = new UserInformationCollection(updatedBasicInfo, updatedContactInfo);

		userRepository.updateUser("010101-1234", updatedUser);

		UserInformationCollection fetchedUser = userRepository.getAllUsers().get(0);
		assertEquals("Matti", fetchedUser.getBasicInformation().getFirstName());
		assertEquals("Virtanen", fetchedUser.getBasicInformation().getSurname());
		assertEquals("040-987654", fetchedUser.getContactInformation().getPhoneNumber());
		assertEquals("Virtasenkatu", fetchedUser.getContactInformation().getStreetAddress());
		assertEquals("Tampere", fetchedUser.getContactInformation().getCity());
	}

	@Test
	public void Test_UpdateUser_UserNotFoundByIdentityCode() {

		UserBasicInformation updatedBasicInfo = new UserBasicInformation("Matti", "Virtanen", "020202-5678", "Suomi", "Mies");
		UserContactInformation updatedContactInfo = new UserContactInformation("020202-5678", "matti.virtanen@esimerkki.com", "040-987654", "Virtasenkatu", "Tampere", "33100");
		UserInformationCollection updatedUser = new UserInformationCollection(updatedBasicInfo, updatedContactInfo);

		assertThrows(IllegalArgumentException.class, () -> {
			userRepository.updateUser("999999-9999", updatedUser);
		}, USER_NOT_FOUND_MESSAGE);
	}

	@Test
	public void Test_GetUserByPersonalIdentityCode() {

		UserInformationCollection foundUser = userRepository.getUserByPersonalIdentityCode("010101-1234");

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
		UserBasicInformation basicInfo = new UserBasicInformation("Matti", "Meikäläinen", "010101-1234", "Suomi", "Mies");
		UserContactInformation contactInfo = new UserContactInformation("010101-1234", "matti.meikalainen@esimerkki.com", "040-123456", "Esimerkkikatu", "Esimerkki", "00000");
		UserInformationCollection user = new UserInformationCollection(basicInfo, contactInfo);

		IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
			userRepository.addUser(user);
		});
		assertEquals(UserInformationService.DUPLICATE_ID_MESSAGE, thrown.getMessage());
	}
}
