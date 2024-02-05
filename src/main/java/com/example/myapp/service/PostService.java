package com.example.myapp.service;

import com.example.myapp.domain.Course;
import com.example.myapp.domain.Post;
import com.example.myapp.domain.PostFile;
import com.example.myapp.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class PostService{

    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public void join(Post post, MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            String filePath = "/Users/choejiho/Downloads/LMS_Spring/src/main/resources/static/uploads";
            UUID uuid = UUID.randomUUID();
            String fileName = uuid + "_" + file.getOriginalFilename();
            File saveFile = new File(filePath,fileName);

            file.transferTo(saveFile);

            PostFile postFile = new PostFile();
            postFile.setFileName(fileName);
            postFile.setFilePath("static/uploads/" + fileName);
            postFile.setFileSize(file.getSize());
            post.setFile(postFile);
            postRepository.saveFile(postFile);
        }
        postRepository.savePost(post); // Post 저장
    }


    public Long update(Post post) {
        postRepository.update(post);
        return post.getId();
    }

//    private void validateDuplicatePost(Post post) {
//        List<Post> result = postRepository.findByTitle(post.getTitle());
//        result.set(m -> {
//            throw new IllegalArgumentException("이미 존재하는 글입니다.");
//        });
//    }

    public List<Post> findPosts() {
        return postRepository.findAll();
    }

    public List<Course> findCourse() {
        return postRepository.findAllCourse();
    }

    public Optional<Post> findOne(Long id) {
        return postRepository.findById(id);
    }

    public int delete(Long id) {
        return postRepository.deleteById(id);
    }

    public Post savePost(Post post) {
        return postRepository.savePost(post);
    }

    public Optional<Post> findById(Long id) {
        return postRepository.findById(id);
    }

    public List<Post> findByTitle(String title) {
        return postRepository.findByTitle(title);
    }

    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public Long findPreviousPostId(Long currentPostId) {
        return postRepository.findPreviousPostId(currentPostId);
    }

    public Long findNextPostId(Long currentPostId) {
        return postRepository.findNextPostId(currentPostId);
    }
    public List<Post> findByTitleContaining(String title) {
        return postRepository.findByTitleContaining(title);
    }
}
