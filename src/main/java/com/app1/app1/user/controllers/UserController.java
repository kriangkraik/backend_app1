package com.app1.app1.user.controllers;

import com.app1.app1.user.entities.User;
import com.app1.app1.user.exceptions.UserAlreadyExistException;
import com.app1.app1.user.services.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin(origins = "${app.cors.allowedOrigins:http://localhost:4200}")
@RequiredArgsConstructor
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    @GetMapping("/all")
    public ResponseEntity<List<User>> getUsers() {
        LOGGER.info("getUsers endpoint complete.");
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        LOGGER.info("Accessed /{} getUserById endpoint complete.", id);
        return userService.getUserById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public Page<User> getUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy) {
        LOGGER.info("getUsersPage endpoint complete.");
        return userService.getUsers(page, size, sortBy);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@Validated @RequestBody User user) throws UserAlreadyExistException {
        LOGGER.info("Post CreateUser endpoint complete.");
        return ResponseEntity.ok(userService.createUser(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        LOGGER.info("Accessed /{} updateUser endpoint complete.", id);
        User updatedUser = userService.updateUser(id, userDetails);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        try {
            userService.deleteUser(id);
            LOGGER.info("Accessed /{} deleteUser endpoint complete.", id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            LOGGER.info("Accessed /{} deleteUser endpoint not complete. Exception: {}", id, e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

}
