package com.example.demo;

import lombok.Data;

@Data
public class LunchDto {
    private String menuName;
    private int cost;
    private String menuCategory;
    private String imageBase64; // 画面表示用のBase64画像

}
