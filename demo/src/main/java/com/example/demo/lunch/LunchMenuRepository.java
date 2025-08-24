package com.example.demo.lunch;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LunchMenuRepository extends JpaRepository<LunchMenu, Long> {

	List<LunchMenu> findByUserId(int userId);
}
