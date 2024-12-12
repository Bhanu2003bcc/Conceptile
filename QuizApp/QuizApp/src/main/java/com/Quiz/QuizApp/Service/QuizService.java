package com.Quiz.QuizApp.Service;

import com.Quiz.QuizApp.Entity.Question;
import com.Quiz.QuizApp.Entity.Session;
import com.Quiz.QuizApp.Entity.UserAnswer;
import com.Quiz.QuizApp.Repository.QuestionRepo;
import com.Quiz.QuizApp.Repository.SessionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class QuizService {
    @Autowired
    private QuestionRepo questionRepo;

    @Autowired
    private SessionRepo sessionRepo;

    private static final String Session_id = "user_session";

    public String StartNewSession(){
        sessionRepo.deleteById(Session_id);
        Session session = new Session(Session_id);
        sessionRepo.save(session);
        return "New quiz session started.";
    }

    public Question getRandomQuestion() {
        List<Question> questions = questionRepo.findAll();
        if (questions.isEmpty()) {
            throw new RuntimeException("No questions available in the database.");
        }
        return questions.get(new Random().nextInt(questions.size()));
    }

    public String SubmitAnswer(Long QuestionId, String Answer){
        Session session = sessionRepo.findById(Session_id).orElseThrow(()-> new RuntimeException("No Active Session Found"));
        Optional<Question> questionOpt = questionRepo.findById(QuestionId);
        if(questionOpt.isEmpty()){
            throw new IllegalArgumentException("Invalid Question Id");
        }
        Question question = questionOpt.get();
        boolean isCorrect = question.getCorrectAnswer().equalsIgnoreCase(Answer);
        session.getAnswers().add(new UserAnswer(question, Answer, isCorrect));
        sessionRepo.save(session);
        return isCorrect? "Correct answer" : "Incorrect Answer";
    }


    public Map<String, Object> getResults() {
        Session session = sessionRepo.findById(Session_id)
                .orElseThrow(() -> new RuntimeException("No active session found."));

        Map<String, Object> results = new HashMap<>();
        results.put("totalQuestions", session.getAnswers().size());
        results.put("correctAnswers", session.getAnswers().stream().filter(a -> a.isCorrect()).count());
        results.put("incorrectAnswers", session.getAnswers().stream().filter(a -> !a.isCorrect()).count());

        return results;
    }

    public Map<String, Object> getHint(Long questionId) {
        Optional<Question> questionOpt = questionRepo.findById(questionId);
        if (questionOpt.isEmpty()) {
            throw new IllegalArgumentException("Invalid question ID.");
        }
        Question question = questionOpt.get();
        List<String> options = List.of(
                question.getOptionA(),
                question.getOptionB(),
                question.getOptionC(),
                question.getOptionD()
        );

        List<String> reducedOptions = new ArrayList<>(options);
        reducedOptions.removeIf(option -> option.equalsIgnoreCase(getCorrectOption(question)));
        Collections.shuffle(reducedOptions);
        while (reducedOptions.size() > 2) {
            reducedOptions.remove(0);
        }
        reducedOptions.add(getCorrectOption(question));

        Collections.shuffle(reducedOptions);

        Map<String, Object> hint = new HashMap<>();
        hint.put("question", question.getText());
        hint.put("options", reducedOptions);
        return hint;
    }

    private String getCorrectOption(Question question) {
        switch (question.getCorrectAnswer().toUpperCase()) {
            case "A":
                return question.getOptionA();
            case "B":
                return question.getOptionB();
            case "C":
                return question.getOptionC();
            case "D":
                return question.getOptionD();
            default:
                throw new IllegalArgumentException("Invalid correct answer code in question.");
        }
    }
}
