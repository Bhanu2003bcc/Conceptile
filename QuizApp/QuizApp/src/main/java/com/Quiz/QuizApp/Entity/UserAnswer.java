package com.Quiz.QuizApp.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Data
//@NoArgsConstructor
public class UserAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Question question;
    @Getter
    private String userAnswer;
    private boolean isCorrect;

    public UserAnswer(){

    }

    public boolean isCorrect() {
        return isCorrect;
    }


    public UserAnswer(Question question, String userAnswer, boolean isCorrect) {
        this.question = question;
        this.userAnswer = userAnswer;
        this.isCorrect = isCorrect;
    }
}
