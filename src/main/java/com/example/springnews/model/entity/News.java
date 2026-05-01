package com.example.springnews.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(length = 30)
    private String writer;
    @Column(length = 50)
    private String title;
    private String content;
    // 글 작성 시간으로 자동 설정되도록 애노테이션 설정
    @CreationTimestamp
    @Column(name = "writedate")
    private LocalDateTime writeDate;
    private int cnt;
}
