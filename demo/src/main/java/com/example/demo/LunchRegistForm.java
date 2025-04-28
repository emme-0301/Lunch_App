package com.example.demo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LunchRegistForm {

    @NotBlank(message = "メニュー名は必須です")
    private String menuName;

    @NotNull(message = "価格は必須です")
    private Integer cost;

    private String menuCategory;
}
