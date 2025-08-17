package com.example.demo.lunch;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LunchMenuRepository extends JpaRepository<LunchMenu, Long> {
}
