package com.Quiz.QuizApp;

import com.Quiz.QuizApp.Entity.Question;
import com.Quiz.QuizApp.Repository.QuestionRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class QuizApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuizApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(QuestionRepo questionRepository) {
		return args -> {
			questionRepository.saveAll(List.of(
					new Question("What is the capital of France?", "Berlin", "Madrid", "Paris", "Rome", "C"),
					new Question("What is 2 + 2?", "3", "4", "5", "6", "B"),
					new Question("What is the color of the sky?", "Blue", "Green", "Red", "Yellow", "A"),
					new Question("What is Spring in computer Science?", "iron Ring","Method","FrameWork","All of These","C")
			));
			System.out.println("Data seeded Successfully!");
		};
	}

}
