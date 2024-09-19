package user.information.collection;

import user.information.collection.userInformation.UserInformationCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserInformationController {

    @Autowired
    private UserInformationService userInformationService;

    @GetMapping("/all")
    public List<UserInformationCollection> findUsers() {
        return userInformationService.getAllUsers();
    }
}
