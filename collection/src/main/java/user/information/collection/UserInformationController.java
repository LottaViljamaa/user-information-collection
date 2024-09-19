package user.information.collection;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserInformationController {

    @GetMapping("/")
    public String index() {
        return "Hello World!";
    }
}
