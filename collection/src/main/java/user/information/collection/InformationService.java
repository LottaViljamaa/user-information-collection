package user.information.collection;

import user.information.collection.userInformation.InformationCollection;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InformationService {

    // Lista käyttäjän tiedoille
    private List<InformationCollection> userDetailsList;

    public InformationService() {
        // Alustetaan lista
        this.userDetailsList = new ArrayList<>();
    }

    public static final String USER_NOT_FOUND_MESSAGE = "User not found with the provided personal identity code.";
    public static final String DUPLICATE_ID_MESSAGE = "User with this identity code already exist.";

    // Metodi kaikkien käyttäjien hakemiseksi
    public List<InformationCollection> getAllUsers() {
        return userDetailsList;
    }

    // Metodi käyttäjän tietojen hakemiselle henkilötunnuksen perusteella
    public InformationCollection getUserByPersonalIdentityCode(String personalIdentityCode) {
        return userDetailsList.stream()
                .filter(user -> user.getBasicInformation().getPersonalIdentityCode().equals(personalIdentityCode))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(USER_NOT_FOUND_MESSAGE));
    }

    // Metodi käyttäjän tietojen lisäämiseksi listaan
    public void addUser(InformationCollection userDetails) {
        String personalIdentityCode = userDetails.getBasicInformation().getPersonalIdentityCode();
        if (userDetailsList.stream().anyMatch(user -> user.getBasicInformation().getPersonalIdentityCode().equals(personalIdentityCode))) {
            throw new IllegalArgumentException(DUPLICATE_ID_MESSAGE);
        }
        userDetailsList.add(userDetails);
    }

    // Metodi käyttäjän poistamiseksi henkilötunnuksen perusteella
    public boolean removeUserByPersonalIdentityCode(String personalIdentityCode) {
        Optional<InformationCollection> userToRemove = userDetailsList.stream()
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
    public boolean updateUser(String personalIdentityCode, InformationCollection updatedUserDetails) {
        Optional<InformationCollection> userToUpdate = userDetailsList.stream()
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
}