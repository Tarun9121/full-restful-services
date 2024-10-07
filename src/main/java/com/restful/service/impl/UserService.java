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
        userRepository.save(user);
        return userDto;
    }

    public User findByIdAndIsDeletedIsFalse(UUID userId) {
        return userRepository.findByIdAndIsDeletedIsFalse(userId.toString()).orElse(null);
    }

    public List<UserDTO> saveAllUsers(List<UserDTO> usersDtoList) {
        List<User> usersList = new ArrayList<>();
        BeanUtils.copyProperties(usersDtoList, usersList);
        userRepository.saveAll(usersList);
        return usersDtoList;
    }

    public Optional<User> getUserById(UUID userId) {
        return userRepository.findByIdAndIsDeletedIsFalse(userId.toString());
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
        Optional<User> existingData = userRepository.findByIdAndIsDeletedIsFalse(userId.toString());

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
        Optional<User> existingUser = userRepository.findByIdAndIsDeletedIsFalse(userId.toString());

        if(existingUser.isEmpty()) {
            return "user cannot found with the given id: " + userId;
        }
        else {
            userRepository.disableAccount(userId.toString());
            return "user deleted successfully with the id: " + userId;
        }
    }

    /**
     * WRITING NATIVE QUERIES WITH EntityManager:
     * @Use - .creativeNativeQuery(nativeQuery)
     * @Note - the above method will not map the result to the entity class, you will get Object[]. if you want to map the result to the entity then use .createNativeQuery(nativeQuery, Entity.class)
     * @Params - if you have any params in the native query then use .setParamater("param1", param1)
     * @Get_Result - if the query returns a list of rows use .getResultList(), if it returns one row then use .getSingleResult()
     */

    @Override
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
