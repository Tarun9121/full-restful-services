package com.restful.controller;

import com.restful.dto.UserDTO;
import com.restful.entity.User;
import com.restful.service.impl.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public UserDTO postUser(@RequestBody UserDTO user) {
//        userService.logUserId("id1");
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
        Optional<User> existingUser = userService.getUserById(userId);
        if(existingUser.isPresent()) {
            UserDTO existingUserDto = new UserDTO();
            BeanUtils.copyProperties(existingUser.get(), existingUserDto);
            userService.logUserId(userId);
            return ResponseEntity.status(HttpStatus.OK).body(existingUserDto);
        }
        else return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @GetMapping("/test")
    public String test() throws ExecutionException, InterruptedException {
        Future<String> status = userService.isMailsentSuccessfully();

        while(true) {
            if(status.isDone()) {
                return status.get();
            }
        }
    }

    @GetMapping("/check/{userId}")
    public Boolean checkUser(@PathVariable("userId")UUID userId) {
        return userService.isPresent(userId);
    }

    @GetMapping("/is/{userId}")
    public User findByIdAndIsDeleted(@PathVariable("userId") UUID userId) {
        return userService.findByIdAndIsDeletedIsFalse(userId);
    }

    @GetMapping("/orderby/age")
    public List<UserDTO> getAllUsersOrderByAge() {
        return userService.getAllUsersOrderByAge();
    }

    @GetMapping("/age/{userAge}")
    public List<UserDTO> getUsersByAge(@PathVariable("userAge") int userAge) {
        return userService.getUsersByAge(userAge);
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
    public String deleteUser(@PathVariable("userId") UUID userId) {
        return userService.deleteUser(userId);
    }

}
