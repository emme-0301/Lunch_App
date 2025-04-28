package com.example.demo;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "MS_LUNCH_MENU")
public class LunchMenu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MENU_ID")
    private Long menuId;

    @Column(name = "MENU_NAME", nullable = false)
    private String menuName;

    @Column(name = "COST")
    private Integer cost;

    @Column(name = "MENU_CATEGORY")
    private String menuCategory;

    @CreationTimestamp
    @Column(name = "CREATE_TSTAMP", updatable = false)
    private LocalDateTime createTstamp;

    @UpdateTimestamp
    @Column(name = "UPDATE_TSTAMP")
    private LocalDateTime updateTstamp;
}
