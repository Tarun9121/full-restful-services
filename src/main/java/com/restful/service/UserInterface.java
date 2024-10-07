package com.restful.service;

import com.restful.dto.UserDTO;
import com.restful.entity.User;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserInterface {
    /**
     * save the user to the users table
     * @param userDto - the data of the user
     * @return - if the data is successfully saved then it will return the same data with id
     */
    UserDTO postUser(UserDTO userDto);

    /**
     * saves all the users at same time to the database
     * @param usersDtoList - list of all the users
     * @return - returns the list with the generated id's
     */
    public List<UserDTO> saveAllUsers(List<UserDTO> usersDtoList);

    /**
     * find the user details based on the id
     * @param userId - id of the user
     * @return - if the data is present then it will send the user details, if not it will send 404-status with null body
     */
    public Optional<User> getUserById(UUID userId);

    /**
     * tells if the user is present or not
     * @param userId - id of the user
     * @return - if present true or else false
     */
    boolean isPresent(UUID userId);

    /**
     * returns all the users in specific order
     * @return - list of users ordered by age
     */
    List<UserDTO> getAllUsersOrderByAge();

    /**
     * details of all users
     * @return - it will return the details of all the users
     */
    public List<UserDTO> getAll();

    /**
     * If the data is present it will update, or it will send the status-404 with the null body
     * @param userId - id of the user
     * @param updatedUser - data to be updated
     * @return - if the user is found it will be updated else it will send the status-404 with the null
     */
    public ResponseEntity<UserDTO> updateUser(UUID userId, UserDTO updatedUser);

    /**
     * It will delete the user
     * @param userId - id of the user
     * @return - returns the appropriate message weather the user is deleted or not
     */
    public String deleteUser(UUID userId);

    /**
     * gets the list of users by age
     * @param age - age of the user
     * @return - returns a list of users with same age
     */
    public List<UserDTO> getUsersByAge(int age);
}