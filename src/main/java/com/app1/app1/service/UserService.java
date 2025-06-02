package com.app1.app1.service;

import org.springframework.data.domain.Page;

import com.app1.app1.entities.User;
import com.app1.app1.exceptions.UserAlreadyExistException;

import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.Optional;

/**
* Service interface for managing user operations.
* 
* This interface defines the contract for user-related business logic,
* including CRUD operations and user management functionality.
* 
* @author Kriangkrai Ketkun
* @version 1.0
* @since 1.0
*/
public interface UserService {
   
   /**
    * Creates a new user in the system.
    * 
    * @param newUser the user entity to be created, must not be null
    * @return the created user with generated ID and timestamps
    * @throws UserAlreadyExistException if a user with the same email or username already exists
    * @throws IllegalArgumentException if newUser is null or contains invalid data
    */
   User createUser(User newUser) throws UserAlreadyExistException;

   /**
    * Retrieves all users from the system.
    * 
    * @return a list of all users, empty list if no users exist
    */
   List<User> getAllUsers();

   /**
    * Retrieves users with pagination and sorting support.
    * 
    * @param page the page number to retrieve (0-based)
    * @param size the number of users per page, must be positive
    * @param sortBy the field name to sort by (e.g., "name", "email", "createdAt")
    * @return a page containing users matching the pagination criteria
    * @throws IllegalArgumentException if page is negative, size is not positive, or sortBy is invalid
    */
   Page<User> getUsers(int page, int size, String sortBy);

   /**
    * Retrieves a user by their unique identifier.
    * 
    * @param id the unique identifier of the user, must not be null
    * @return an Optional containing the user if found, empty Optional otherwise
    * @throws IllegalArgumentException if id is null
    */
   Optional<User> getUserById(Long id);

   /**
    * Updates an existing user with new information.
    * 
    * @param id the unique identifier of the user to update, must not be null
    * @param userDetails the user entity containing updated information, must not be null
    * @return the updated user entity
    * @throws EntityNotFoundException if no user exists with the specified id
    * @throws IllegalArgumentException if id or userDetails is null
    */
   User updateUser(Long id, User userDetails) throws EntityNotFoundException;

   /**
    * Deletes a user from the system.
    * 
    * @param id the unique identifier of the user to delete, must not be null
    * @throws EntityNotFoundException if no user exists with the specified id
    * @throws IllegalArgumentException if id is null
    */
   void deleteUser(Long id) throws EntityNotFoundException;
}