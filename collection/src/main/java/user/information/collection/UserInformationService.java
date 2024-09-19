package user.information.collection;

import user.information.collection.userInformation.UserInformationCollection;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserInformationService {

    // Lista käyttäjän tiedoille
    private List<UserInformationCollection> userDetailsList;

    public UserInformationService() {
        // Alustetaan lista
        this.userDetailsList = new ArrayList<>();
    }

    public static final String USER_NOT_FOUND_MESSAGE = "User not found with the provided personal identity code.";
    public static final String DUPLICATE_ID_MESSAGE = "User with this identity code already exist.";

    // Metodi käyttäjän tietojen lisäämiseksi listaan
    public void addUser(UserInformationCollection userDetails) {
        String personalIdentityCode = userDetails.getBasicInformation().getPersonalIdentityCode();
        if (userDetailsList.stream().anyMatch(user -> user.getBasicInformation().getPersonalIdentityCode().equals(personalIdentityCode))) {
            throw new IllegalArgumentException(DUPLICATE_ID_MESSAGE);
        }
        userDetailsList.add(userDetails);
    }

    // Metodi kaikkien käyttäjien hakemiseksi
    public List<UserInformationCollection> getAllUsers() {
        return userDetailsList;
    }

    // Metodi käyttäjän poistamiseksi henkilötunnuksen perusteella
    public boolean removeUserByPersonalIdentityCode(String personalIdentityCode) {
        Optional<UserInformationCollection> userToRemove = userDetailsList.stream()
                .filter(user -> user.getBasicInformation().getPersonalIdentityCode().equals(personalIdentityCode))
                .findFirst();

        if (userToRemove.isPresent()) {
            userDetailsList.remove(userToRemove.get());
            return true;
        } else {
            throw new IllegalArgumentException(USER_NOT_FOUND_MESSAGE);
        }
    }

    // Metodi käyttäjän tietojen päivittämiseksi henkilötunnuksen perusteella
    public boolean updateUser(String personalIdentityCode, UserInformationCollection updatedUserDetails) {
        Optional<UserInformationCollection> userToUpdate = userDetailsList.stream()
                .filter(user -> user.getBasicInformation().getPersonalIdentityCode().equals(personalIdentityCode))
                .findFirst();

        if (userToUpdate.isPresent()) {
            int index = userDetailsList.indexOf(userToUpdate.get());
            userDetailsList.set(index, updatedUserDetails);
            return true;
        } else {
            throw new IllegalArgumentException(USER_NOT_FOUND_MESSAGE);
        }
    }

    // Metodi käyttäjän tietojen hakemiselle henkilötunnuksen perusteella
    public UserInformationCollection getUserByPersonalIdentityCode(String personalIdentityCode) {
        return userDetailsList.stream()
                .filter(user -> user.getBasicInformation().getPersonalIdentityCode().equals(personalIdentityCode))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(USER_NOT_FOUND_MESSAGE));
    }
}