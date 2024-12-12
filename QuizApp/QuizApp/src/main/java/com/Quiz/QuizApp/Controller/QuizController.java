package com.Quiz.QuizApp.Controller;

import com.Quiz.QuizApp.Service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @PostMapping("/start")
    public ResponseEntity<String> StartNewQuiz(){
        try{
            return ResponseEntity.ok(quizService.StartNewSession());
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to Start a new session: " + e.getMessage());
        }
    }

    @GetMapping("/question")
    public ResponseEntity<?> getRandomQuestion() {
        try {
            return ResponseEntity.ok(quizService.getRandomQuestion());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error fetching question: " + e.getMessage());
        }
    }

    @PostMapping("/answer")
    public ResponseEntity<String> submitAnswer(@RequestParam Long questionId, @RequestParam String answer) {
        try {
            return ResponseEntity.ok(quizService.SubmitAnswer(questionId, answer));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error submitting answer: " + e.getMessage());
        }
    }

    @GetMapping("/results")
    public ResponseEntity<?> getResults() {
        try {
            return ResponseEntity.ok(quizService.getResults());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error fetching results: " + e.getMessage());
        }
    }

    @GetMapping("/hint")
    public ResponseEntity<?> getHint(@RequestParam Long questionId) {
        try {
            return ResponseEntity.ok(quizService.getHint(questionId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error generating hint: " + e.getMessage());
        }
    }

}
