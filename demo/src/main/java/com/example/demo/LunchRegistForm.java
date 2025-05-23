package com.example.demo;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LunchRegistForm {

    @NotBlank(message = "メニュー名は必須です")
    private String menuName;

    private MultipartFile image;
    
    @NotNull(message = "価格は必須です")
    private Integer cost;

    private String menuCategory;
}
