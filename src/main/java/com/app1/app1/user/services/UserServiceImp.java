package com.app1.app1.user.services;

import com.app1.app1.user.entities.User;
import com.app1.app1.user.exceptions.UserAlreadyExistException;
import com.app1.app1.user.exceptions.UserNotFoundException;
import com.app1.app1.user.repositories.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Primary
@RequiredArgsConstructor
public class UserServiceImp implements UserService {

	private final UserRepository userRepository;

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	public Page<User> getUsers(int page, int size, String sortBy) {
		Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());
		return userRepository.findAll(pageable);
	}

	public Optional<User> getUserById(Long id) {
		return userRepository.findById(id);
	}

	public User createUser(User newUser) throws UserAlreadyExistException {
		if (userRepository.existsByEmail(newUser.getEmail())) {
			throw new UserAlreadyExistException("User already exists with email: " + newUser.getEmail());
		}
		return userRepository.save(newUser);
	}

	public User updateUser(Long id, User userDetails) {
		return userRepository.findById(id).map(user -> {
			Optional.ofNullable(userDetails.getFirstname()).ifPresent(user::setFirstname);
			Optional.ofNullable(userDetails.getLastname()).ifPresent(user::setLastname);
			Optional.ofNullable(userDetails.getEmail()).ifPresent(user::setEmail);
			Optional.ofNullable(userDetails.getPassword()).ifPresent(user::setPassword);
			Optional.ofNullable(userDetails.getPhoneNumber()).ifPresent(user::setPhoneNumber);
			Optional.ofNullable(userDetails.getAddress()).ifPresent(user::setAddress);
			Optional.ofNullable(userDetails.getCity()).ifPresent(user::setCity);
			Optional.ofNullable(userDetails.getState()).ifPresent(user::setState);
			Optional.ofNullable(userDetails.getCountry()).ifPresent(user::setCountry);
			Optional.ofNullable(userDetails.getZipCode()).ifPresent(user::setZipCode);

			return userRepository.save(user);
		}).orElseThrow(() -> new UserNotFoundException("User not found with id " + id));
	}

	public void deleteUser(Long id) {
		if (!userRepository.existsById(id)) {
			throw new UserNotFoundException("User not found with id " + id);
		}
		userRepository.deleteById(id);
	}

}
