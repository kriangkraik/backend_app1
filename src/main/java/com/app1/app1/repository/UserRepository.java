package com.app1.app1.repository;

import org.springframework.stereotype.Repository;

import com.app1.app1.model.User;

@Repository
public interface UserRepository extends PagingAndSortingUserRepository<User, Long> {

}
