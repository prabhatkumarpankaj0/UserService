package user_module.user.user;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping
    public List<User> getAllUser(){

        return userService.getAllUser();
    }

    @GetMapping(path = "login")
    public User login(@RequestParam(required = true) String email, @RequestParam(required = true) String password){

        return userService.getUser(email, password);
    }

    @PostMapping
    public void registerNewUser(@RequestBody User user){

        userService.addNewUser(user);

    }
}
