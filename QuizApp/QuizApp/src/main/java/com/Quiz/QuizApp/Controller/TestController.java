package com.Quiz.QuizApp.Controller;

import com.Quiz.QuizApp.Entity.Question;
import com.Quiz.QuizApp.Repository.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Repository
@RequestMapping("/api/debug")
public class TestController {

    @Autowired
    private QuestionRepo questionRepo;

    @GetMapping("questions")
    public ResponseEntity<List<Question>> getAllQuestions() {
        return ResponseEntity.ok(questionRepo.findAll());
    }
}
