package com.app1.app1.repository;

import com.app1.app1.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends PagingAndSortingUserRepository<User, Long> {}
