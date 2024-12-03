package com.example.usersmanagementssoftware.Controller;

import com.example.usersmanagementssoftware.ApiResponse.ApiResponse;
import com.example.usersmanagementssoftware.Model.User;
import com.example.usersmanagementssoftware.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/get")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addUser(@RequestBody @Valid User user, Errors errors) {
        if (errors.hasErrors()) {
            String errorMessage = errors.getAllErrors().get(0).getDefaultMessage();
            ResponseEntity.status(400).body(new ApiResponse(errorMessage));
        }

        userService.addUser(user);
        return ResponseEntity.status(200).body(new ApiResponse("user added successfully"));
    }


    @PutMapping("/update/{userId}")
    public ResponseEntity<ApiResponse> updateUser(@PathVariable Integer userId, @RequestBody @Valid User user, Errors errors) {
        if (errors.hasErrors()) {
            String errorMessage = errors.getAllErrors().get(0).getDefaultMessage();
            ResponseEntity.status(400).body(new ApiResponse(errorMessage));
        }
        userService.updateUser(userId, user);
        return ResponseEntity.status(200).body(new ApiResponse("user updated successfully"));
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer userId) {
        userService.deleteUser(userId);
        return ResponseEntity.status(200).body(new ApiResponse("user deleted successfully"));

    }

    @GetMapping("/check/{username}/{password}")
    public ResponseEntity<ApiResponse> checkUserNameAndPassword(@PathVariable String username, @PathVariable String password) {
        userService.checkUserInfo(username, password);
        return ResponseEntity.status(200).body(new ApiResponse("username, password are correct"));
    }

    @GetMapping("/get-getUserByEmail/{email}")
    public User getUserByEmail(@PathVariable String email) {
       return userService.getUserByEmail(email);
    }

    @GetMapping("/get-getAllUsersByRole/{role}")
    public List<User> getAllUsersByRole(@PathVariable String role) {
        return userService.getUsersByRole(role);
    }

    @GetMapping("/get-getAllUsersByAge/{age}")
    public List<User> getAllUsersByAge(@PathVariable Integer age) {
        return userService.getUsersByAge(age);
    }

}
