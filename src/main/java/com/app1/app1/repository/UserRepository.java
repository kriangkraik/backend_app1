package com.app1.app1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app1.app1.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
