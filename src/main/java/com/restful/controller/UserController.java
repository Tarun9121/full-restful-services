package com.restful.controller;

import com.restful.dto.UserDTO;
import com.restful.entity.User;
import com.restful.service.impl.UserService;
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
    public UserDTO postUser(@RequestBody UserDTO user) {
        return userService.postUser(user);
    }

    @PostMapping("/all")
    public List<UserDTO> postAllUsers(@RequestBody List<UserDTO> usersList) {
        return userService.saveAllUsers(usersList);
    }

    @GetMapping
    public List<UserDTO> getAll() {
        return userService.getAll();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("userId") UUID userId) {
        return userService.getUserById(userId);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable("userId") UUID userId, @RequestBody UserDTO updatedUser) {
        return userService.updateUser(userId, updatedUser);
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<UserDTO> patchUser(@PathVariable("userId") UUID userId, @RequestBody UserDTO udpateUser) {
        return userService.updateUser(userId, udpateUser);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable("userId") UUID userId) {
        return userService.deleteUser(userId);
    }

}
