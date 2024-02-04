package com.example.myapp.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "qna_files") // 테이블 이름을 "qna"로 설정
@NoArgsConstructor
public class PostFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fileName;
    private String filePath;
    private long fileSize;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post; // Post와 PostFile을 일대일 관계로 매핑합니다.

    public PostFile(String fileName, String filePath, long fileSize) {
        this.fileName = fileName;
        this.filePath = filePath;
        this.fileSize = fileSize;
    }
}