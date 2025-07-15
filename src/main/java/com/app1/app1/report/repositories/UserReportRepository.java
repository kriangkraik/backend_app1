package com.app1.app1.report.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app1.app1.user.entities.User;

public interface UserReportRepository extends JpaRepository<User, Long> {

}
