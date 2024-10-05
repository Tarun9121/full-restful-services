package com.restful.service;

import com.restful.dto.UserDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;
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
    public ResponseEntity<UserDTO> getUserById(UUID userId);

    /**
     * details of all users
     * @return - it will return the details of all the users
     */
    public List<UserDTO> getAll();

    /**
     * If the data is present the it will update or it will send the status-404 with the null body
     * @param userId - id of the user
     * @param updatedUser - data to be updated
     * @return - if the user is found it will be updated else it will send the status-404 with the null
     */
    public ResponseEntity<UserDTO> updateUser(UUID userId, UserDTO updatedUser);

    /**
     * It will delete the user
     * @param userId - id of the user
     * @return - if the account is deleted then it will return status-200, it is already deleted then it will send the 406-status, if the user is not found then it will return status-400 with appropriate message
     */
    public ResponseEntity<String> deleteUser(UUID userId);
}
