package com.app1.app1.user.repositories;

import org.springframework.stereotype.Repository;

import com.app1.app1.user.entities.User;

@Repository
public interface UserRepository extends PagingAndSortingUserRepository<User, Long> {
    boolean existsByEmail(String email);
}
