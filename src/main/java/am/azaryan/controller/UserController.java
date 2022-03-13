package am.azaryan.controller;

import am.azaryan.model.Role;
import am.azaryan.model.User;
import am.azaryan.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @RequestMapping(value = "/team-members", method = RequestMethod.GET)
    private List<User> getAllTeamMembers() {
        return userService.findByRole(Role.TEAM_MEMBER);
    }

    @RequestMapping(value = "/all-users", method = RequestMethod.GET)
    private ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok(userService.findAllUsers());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    private ResponseEntity<?> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.findUserById(id));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    private void deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);
    }
}
