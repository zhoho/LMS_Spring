package com.example.myapp.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;


@Getter
@Setter
@Entity
@Table(name = "qna")
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String content;
    private Date date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id") // 외래 키를 지정
    private Course course;

    @OneToOne(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private PostFile file;

    public Post(String title, String content, Timestamp date) {
        this.title = title;
        this.content = content;
        this.date = date;
    }
}