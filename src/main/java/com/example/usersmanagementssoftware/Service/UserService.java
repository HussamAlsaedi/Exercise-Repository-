package com.example.usersmanagementssoftware.Service;

import com.example.usersmanagementssoftware.Model.User;
import com.example.usersmanagementssoftware.UserRepository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service @RequiredArgsConstructor
public class UserService {


    private final UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void addUser(User user) {
        userRepository.save(user);
    }

    public void updateUser(Integer userId, User user) {
         User user1 = userRepository.findUserById(userId);

         if (user1 == null) {
             throw new RuntimeException("User not found");
         }
         user1.setName(user.getName());
         user1.setEmail(user.getEmail());
         user1.setPassword(user.getPassword());
         user1.setRole(user.getRole());
         user1 .setAge(user.getAge());
         userRepository.save(user1);
    }

    public void deleteUser(Integer userId) {
        User user = userRepository.findUserById(userId);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        userRepository.delete(user);
    }

    public void checkUserInfo(String username, String password) {

        User user = userRepository.checkUsernameAndPassword(username, password);

        if (user == null) {
            throw new RuntimeException("username, password are incorrect");
        }
    }

    public User getUserByEmail(String userEmail) {
        User user = userRepository.findUserByEmail(userEmail);

        if (user == null) {
            throw new RuntimeException("Email not found");
        }
        return userRepository.findUserByEmail(userEmail);

    }

    public List<User> getUsersByRole(String role) {
        List<User> users = userRepository.findAllUsersByRole(role);
        if (users == null) {
            throw new RuntimeException("there are no users with same role.");
        }
        return users;
    }

    public List<User> getUsersByAge(Integer age) {
        List<User> users =userRepository.findAllUsersByAge(age);

        if (users == null) {
            throw new RuntimeException("there are no users with same age or above.");
        }

        return users;
    }




}
