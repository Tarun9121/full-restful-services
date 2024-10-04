package com.restful.controller;

import com.restful.entity.User;
import com.restful.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public User postUser(@RequestBody User user) {
        return userService.postUser(user);
    }

    @PostMapping("/all")
    public List<User> postAllUsers(@RequestBody List<User> usersList) {
        return userService.saveAllUsers(usersList);
    }

    @GetMapping
    public List<User> getAll() {
        return userService.getAll();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable("userId") UUID userId) {
        return userService.getUserById(userId);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable("userId") UUID userId, @RequestBody User updatedUser) {
        return userService.updateUser(userId, updatedUser);
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<User> patchUser(@PathVariable("userId") UUID userId, @RequestBody User udpateUser) {
        return userService.updateUser(userId, udpateUser);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable("userId") UUID userId) {
        return userService.deleteUser(userId);
    }

}
