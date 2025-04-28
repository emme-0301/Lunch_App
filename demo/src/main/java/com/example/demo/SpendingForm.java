package com.example.demo;

import lombok.Data;

@Data
public class SpendingForm {
	private String userName;
    private Integer month;
    private Integer total_spent;
}
