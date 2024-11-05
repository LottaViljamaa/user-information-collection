package user.information.collection;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<InformationCollection>> findUsers() {
        List<InformationCollection> users = userInformationService.getAllUsers();
        return ResponseEntity.ok(users);
    }
    //Käyttäjätietojen hakeminen henkilötunnuksen perusteella
    @GetMapping("/get/{personalIdentityCode}")
    public ResponseEntity<?> getUserByPersonalIdentityCode(@PathVariable String personalIdentityCode) {
        try {
            InformationCollection user = userInformationService.getUserByPersonalIdentityCode(personalIdentityCode);
            return ResponseEntity.ok(user);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }

    //Uuden käyttäjän lisäys
    @PutMapping("/add")
    public ResponseEntity<String> addUser(@RequestBody InformationCollection userDetails) {
        try {
            userInformationService.addUser(userDetails);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("{\"message\": \"User added successfully.\"}");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }

    //Käyttäjän tietojen poistaminen
    @DeleteMapping("/delete/{personalIdentityCode}")
    public ResponseEntity<String> removeUserByPersonalIdentityCode(@PathVariable String personalIdentityCode) {
        try {
            userInformationService.removeUserByPersonalIdentityCode(personalIdentityCode);
            return ResponseEntity.ok("{\"message\": \"User removed successfully.\"}");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }

    @PutMapping("/update/{personalIdentityCode}")
    public ResponseEntity<String> updateUser(@PathVariable String personalIdentityCode, @RequestBody InformationCollection updatedUserDetails) {
        try {
            boolean isUpdated = userInformationService.updateUser(personalIdentityCode, updatedUserDetails);
            if (isUpdated) {
                return ResponseEntity.ok("{\"message\": \"User updated successfully.\"}");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("{\"error\": \"User not found with the provided personal identity code.\"}");
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }
}
