package com.example.demo.history;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.lunch.LunchMenu;

public interface LunchHistoryRepository extends JpaRepository<LunchMenu, Long> {
}
