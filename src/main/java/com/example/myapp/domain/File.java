package com.example.myapp.domain;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fileName;
    private String filePath;
    private long fileSize;
}