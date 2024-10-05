package com.restful.service.impl;

import com.restful.dto.UserDTO;
import com.restful.entity.User;
import com.restful.repository.UserRepository;
import com.restful.service.UserInterface;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService implements UserInterface {
    @Autowired
    private UserRepository userRepository;

    public UserDTO postUser(UserDTO userDto) {
        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        userRepository.save(user);
        return userDto;
    }

    public List<UserDTO> saveAllUsers(List<UserDTO> usersDtoList) {
        List<User> usersList = new ArrayList<>();
        BeanUtils.copyProperties(usersDtoList, usersList);
        userRepository.saveAll(usersList);
        return usersDtoList;
    }

    public ResponseEntity<UserDTO> getUserById(UUID userId) {

        Optional<User> existingUser = userRepository.findById(userId);

        if(existingUser.isPresent()) {
            UserDTO userData = new UserDTO();
            BeanUtils.copyProperties(existingUser.get(), userData);
            return ResponseEntity.status(HttpStatus.OK).body(userData);
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @Override
    public boolean isPresent(UUID userId) {
        List<User> usersList = userRepository.findAll();
        Optional<User> user = userRepository.findById(userId);

        if(user.isPresent() && !usersList.isEmpty()) {
            return usersList.contains(user.get());
        }
        return false;
    }

    @Override
    public List<UserDTO> getAllUsersOrderByAge() {
        List<UserDTO> usersList = getAll();
//        Comparator<UserDTO> orderByAge = (user1, user2) -> Integer.compare(user1.getAge(), user2.getAge());
        usersList.sort((user1, user2) -> Integer.compare(user1.getAge(), user2.getAge()));
        return usersList;
    }

    public List<UserDTO> getAll() {
        List<User> usersList = userRepository.findAll();
        List<UserDTO> usersDtoList = new ArrayList<>();

        usersList.forEach(user ->  {
            UserDTO dto = new UserDTO();
            BeanUtils.copyProperties(user, dto);
            usersDtoList.add(dto);
        });

        return usersDtoList;
    }

    public ResponseEntity<UserDTO> updateUser(UUID userId, UserDTO updatedUser) {
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
        Optional<Integer> isDeletedInteger = userRepository.findByIdAndIsDeleted(userId.toString());
        Optional<Boolean> isDeletedNonNative = userRepository.isActiveNonNative(userId);
        if(isDeletedNonNative.isPresent()) {
            if(isDeletedNonNative.get()) {
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("The account already deleted");
            }
            else {
                userRepository.disableAccountNonNative(userId);
                return ResponseEntity.status(HttpStatus.OK).body("The account deleted successfully");
            }
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("sorry, your account is not found");
        }
    }
}
