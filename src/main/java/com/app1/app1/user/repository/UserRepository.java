package com.app1.app1.user.repository;

import com.app1.app1.user.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends PagingAndSortingUserRepository<User, Long> {
    boolean existsByEmail(String email);
}
