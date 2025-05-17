package com.example.demo;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class SpendingForm {
	private String userName;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate date;
    private Integer total_spent;
}
