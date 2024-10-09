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
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService implements UserInterface {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EntityManager entityManager;

    public UserDTO postUser(UserDTO userDto) {
        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        User savedUser = userRepository.save(user);
        userDto.setId(savedUser.getId());
        return userDto;
    }

    @Async
    public void logUserId(UUID userId) {
        for(int i = 0; i <= 10; i++) {
            try {
                Thread.sleep(1000);
                System.out.println("loading...");
            }
            catch(InterruptedException exc) {
                exc.printStackTrace();
            }
        }

        System.out.println("please node down the user id: " + userId);
    }

    public User findByIdAndIsDeletedIsFalse(UUID userId) {
        return userRepository.findByIdAndIsDeletedIsFalse(userId).orElse(null);
    }

    public List<UserDTO> saveAllUsers(List<UserDTO> usersDtoList) {
        List<User> usersList = new ArrayList<>();

        usersDtoList.forEach(userDto -> {
            User user = new User();
            BeanUtils.copyProperties(userDto, user);
            usersList.add(user);
        });

        List<User> savedUsersList = userRepository.saveAll(usersList);
        usersDtoList.clear();
        savedUsersList.forEach(user -> {
            UserDTO userDto = new UserDTO();
            BeanUtils.copyProperties(user, userDto);
            usersDtoList.add(userDto);
        });
        return usersDtoList;
    }

    public Optional<User> getUserById(UUID userId) {
        return userRepository.findByIdAndIsDeletedIsFalse(userId);
    }

    public boolean isPresent(UUID userId) {
        List<User> usersList = userRepository.findAll();
        Optional<User> user = userRepository.findById(userId);

        if(user.isPresent() && !usersList.isEmpty()) {
            return usersList.contains(user.get());
        }
        return false;
    }

    public List<UserDTO> getAllUsersOrderByAge() {
        List<UserDTO> usersList = getAll();
        usersList.sort(Comparator.comparingInt(UserDTO::getAge));
        return usersList;
    }

    public List<UserDTO> getAll() {
        List<User> usersList = userRepository.findByIsDeletedIsFalse();
        List<UserDTO> usersDtoList = new ArrayList<>();

        usersList.forEach(user ->  {
            UserDTO dto = new UserDTO();
            BeanUtils.copyProperties(user, dto);
            usersDtoList.add(dto);
        });

        return usersDtoList;
    }

    public ResponseEntity<UserDTO> updateUser(UUID userId, UserDTO updatedUser) {
        Optional<User> existingData = userRepository.findByIdAndIsDeletedIsFalse(userId);

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
    public String deleteUser(UUID userId) {
        Optional<User> existingUser = userRepository.findByIdAndIsDeletedIsFalse(userId);

        if(existingUser.isEmpty()) {
            return "user cannot found with the given id: " + userId;
        }
        else {
            userRepository.disableAccount(userId.toString());
            return "user deleted successfully with the id: " + userId;
        }
    }


    public List<UserDTO> getUsersByAge(int age) {
        List<User> usersList = entityManager
                .createQuery("select u from User u where u.age = :age", User.class)
                .setParameter("age", age)
                .getResultList();
        List<UserDTO> usersDtoList = new ArrayList<>();

        usersList.forEach(user -> {
            UserDTO userDto = new UserDTO();
            BeanUtils.copyProperties(user, userDto);
            usersDtoList.add(userDto);
        });

        return usersDtoList;
    }
}
