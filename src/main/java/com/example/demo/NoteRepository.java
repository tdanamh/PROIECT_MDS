package com.example.demo;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface NoteRepository extends MongoRepository<Note, String> {
    public Note findByTitle(String title);
    public List<Note> findByUserId(String userId);
    public List<Note> findByUserIdAndCategory(String userId, String category);
    public void deleteByUserId(String userId);
}
