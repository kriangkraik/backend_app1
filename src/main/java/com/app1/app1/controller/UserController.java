package com.app1.app1.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app1.app1.model.User;
import com.app1.app1.repository.UserRepository;
import com.app1.app1.service.UserService;

@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin(origins = "${app.cors.allowedOrigins:http://localhost:4200}")
/**
 * UserController is a REST controller that handles HTTP requests
 * for managing User entities, including CRUD operations.
 */
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private final UserRepository userRepository;

    private final UserService userService;

    public UserController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @GetMapping("/all")
    public List<User> getAllUsers() {
        LOGGER.info("Accessed / endpoint complete.");
        return userRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<User> createUser(@Validated @RequestBody User user) {
        LOGGER.info("Post CreateUser endpoint complete.");
        return ResponseEntity.ok(userRepository.save(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        LOGGER.info("Accessed /{} getUserById endpoint complete.", id);
        return userRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public Page<User> getUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy) {
        return userService.getUsers(page, size, sortBy);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        LOGGER.info("Accessed /{} updateUser endpoint complete.", id);
        return userRepository.findById(id)
                .map(user -> {
                    user.setFirstname(userDetails.getFirstname());
                    user.setLastname(userDetails.getLastname());
                    user.setEmail(userDetails.getEmail());
                    user.setPassword(userDetails.getPassword());
                    user.setPhoneNumber(userDetails.getPhoneNumber());
                    user.setAddress(userDetails.getAddress());
                    user.setCity(userDetails.getCity());
                    user.setState(userDetails.getState());
                    user.setCountry(userDetails.getCountry());
                    user.setZipCode(userDetails.getZipCode());
                    LOGGER.info("User with ID {} successfully updated.", id);
                    return ResponseEntity.ok(userRepository.save(user));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        return userRepository.findById(id)
                .map(user -> {
                    try {
                        userRepository.delete(user);
                        LOGGER.info("Accessed /{} deleteUser endpoint Complete.", id);
                        return ResponseEntity.noContent().<Void>build();
                    } catch (Exception e) {
                        LOGGER.error("Error occurred while deleting user with id " + id, e);
                        return ResponseEntity.status(500).<Void>build();
                    }
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
