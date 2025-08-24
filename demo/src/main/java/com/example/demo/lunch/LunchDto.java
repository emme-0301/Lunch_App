package com.example.demo.lunch;

import lombok.Data;

@Data
public class LunchDto {
	private int user_id;
    private String menuName;
    private int cost;
    private String menuCategory;
    private String imageBase64; // 画面表示用のBase64画像

}
