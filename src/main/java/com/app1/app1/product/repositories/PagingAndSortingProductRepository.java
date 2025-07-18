package com.app1.app1.product.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface PagingAndSortingProductRepository<T1, I> extends JpaRepository<T1, I> {
}