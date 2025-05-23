package com.example.demo;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
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
    
    @Lob
    @Column(name = "IMAGE", columnDefinition = "LONGBLOB")
    private byte[] image;

    @Column(name = "IMAGE_TYPE")
    private String imageType;
    
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
    
    @Transient // 永続化しないように（DBに保存しない）
    private String base64Image;

    public String getBase64Image() {
        return base64Image;
    }

    public void setBase64Image(String base64Image) {
        this.base64Image = base64Image;
    }
}
