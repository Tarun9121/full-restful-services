package com.restful.service;

import com.restful.entity.User;
import com.restful.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User postUser(User user) {
        return userRepository.save(user);
    }

}
