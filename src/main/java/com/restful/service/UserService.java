package com.restful.service;

import com.restful.entity.User;
import com.restful.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User postUser(User user) {
        return userRepository.save(user);
    }

//    public void patchUser(UUID userId, User udpatedUser) {
//
//    }

    public ResponseEntity<User> getUserById(UUID userId) {
        Optional<User> existingUser = userRepository.findById(userId);

        if(existingUser.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(existingUser.get());
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public ResponseEntity<User> updateUser(UUID userId, User updatedUser) {
        Optional<User> existingData = userRepository.findById(userId);

        if(existingData.isPresent()) {
            updatedUser.setId(userId);
            postUser(updatedUser);
            return ResponseEntity.status(HttpStatus.OK).body(updatedUser);
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @Transactional
    @Modifying
    public ResponseEntity<String> deleteUser(UUID userId) {
        Optional<Integer> isDeletedInteger = userRepository.isActiveInteger(userId);
        Optional<Boolean> isDeletedBoolean = userRepository.isActiveBoolean(userId);
        Optional<Boolean> isDeletedNonNative = userRepository.isActiveNonNative(userId);
        if(isDeletedNonNative.isPresent()) {
            if(isDeletedNonNative.get()) {
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("The account already deleted");
            }
            else {
                userRepository.disableAccountNonNative(userId);
                return ResponseEntity.status(HttpStatus.OK).body("The accound deleted successfully");
            }
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("sorry, your account is not found");
        }
    }
}
