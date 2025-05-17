package com.example.demo;

import lombok.Data;

@Data
public class GenreStats {
	private int count;
    private int totalCost;

    public GenreStats(int count, int totalCost) {
        this.count = count;
        this.totalCost = totalCost;
    }

}
