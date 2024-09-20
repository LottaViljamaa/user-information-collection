package user.information.collection;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import user.information.collection.userInformation.InformationCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class InformationController {

    @Autowired
    private InformationService userInformationService;

    //Kaikkien käyttäjien tietojen hakeminen
    @GetMapping("/all")
    public List<InformationCollection> findUsers() {
        return userInformationService.getAllUsers();
    }

    //Käyttäjätietojen hakeminen henkilötunnuksen perusteella
    @GetMapping("/get/{personalIdentityCode}")
    @ResponseStatus(HttpStatus.OK)
    public InformationCollection getUserByPersonalIdentityCode(@PathVariable String personalIdentityCode) {
        try {
            return userInformationService.getUserByPersonalIdentityCode(personalIdentityCode);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    //Uuden käyttäjän lisäys
    @PutMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public String addUser(@RequestBody InformationCollection userDetails) {
        try {
            userInformationService.addUser(userDetails);
            return "User added successfully.";
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    //Käyttäjän tietojen poistaminen
    @DeleteMapping("/delete/{personalIdentityCode}")
    @ResponseStatus(HttpStatus.OK)
    public String removeUserByPersonalIdentityCode(@PathVariable String personalIdentityCode) {
        try {
            userInformationService.removeUserByPersonalIdentityCode(personalIdentityCode);
            return "User removed successfully.";
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping("/update/{personalIdentityCode}")
    @ResponseStatus(HttpStatus.OK)
    public String updateUser(@PathVariable String personalIdentityCode, @RequestBody InformationCollection updatedUserDetails) {
        try {
            boolean isUpdated = userInformationService.updateUser(personalIdentityCode, updatedUserDetails);

            if (isUpdated) {
                return "User updated successfully.";
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with the provided personal identity code.");
            }
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}
