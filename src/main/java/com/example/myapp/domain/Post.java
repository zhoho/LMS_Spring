package com.example.myapp.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;


@Getter
@Entity
@Table(name = "qna") // 테이블 이름을 "qna"로 설정
@Setter
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String content;
    private Date date;

    @OneToOne(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private PostFile file; // Post와 PostFile을 일대일 관계로 매핑합니다.

    public Post(String title, String content, Timestamp date) {
        this.title = title;
        this.content = content;
        this.date = date;
    }
}
