package com.app1.app1.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface PagingAndSortingUserRepository<T1, I> extends JpaRepository<T1, I> {
}