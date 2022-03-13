package pl.edu.zut.gk45098backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.zut.gk45098backend.model.User;
import pl.edu.zut.gk45098backend.projection.security.UserReadModel;
import pl.edu.zut.gk45098backend.service.UserService;

@RestController
public class ProfileController {

    private UserService userService;

    public ProfileController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/api/profile")
    public UserReadModel getCurrentUser() {
        User user = userService.getCurrentUser();
        return new UserReadModel(user);
    }

}
