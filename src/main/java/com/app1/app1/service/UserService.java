package com.app1.app1.service;

import com.app1.app1.model.User;

import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> getAllUsers();

    Page<User> getUsers(int page, int size, String sortBy);

    Optional<User> getUserById(Long id);

    User updateUser(Long id, User userDetails);
}
