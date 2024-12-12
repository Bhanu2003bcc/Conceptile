package com.Quiz.QuizApp.Repository;

import com.Quiz.QuizApp.Entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepo extends JpaRepository<Session, String> {
}
