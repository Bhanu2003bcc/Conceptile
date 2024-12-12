#  Quiz App API

A simple quiz application built using Spring Boot and H2 Database. 
This application allows users to start a quiz session, fetch random questions, submit answers, and view results. 
-> It also includes a hint feature to help players by eliminating incorrect options.

## What Does It Do?

The Quiz App has the following features:

Start a Quiz: Begin a new quiz session to track your progress.
Get Random Questions: Fetch random multiple-choice questions from a preloaded database.
Submit Your Answer: Answer questions and find out if you're right or wrong!
View Results: See how many questions you answered correctly and incorrectly.
Get Hints: Use hints to eliminate two incorrect options and improve your chances.

##  Technologies Used

Backend: Spring Boot, 
Database: H2 (In-Memory), 
ORM: JPA/Hibernate, 
Language: Java, 
Testing Tool: Postman or cURL (for API testing)

##  How to Use

API Endpoints

1. Start a New Quiz Session
  Endpoint: POST /api/quiz/start
  Description: Starts a new quiz session for the user.

2. Get a Random Question
  Endpoint: GET /api/quiz/question
  Description: Fetches a random multiple-choice question

3. Submit an Answer
  Endpoint: POST /api/quiz/answer?questionId=1&answer=c
  Parameters:
    questionId (Long): The ID of the question.
    answer (String): The selected option (A, B, C, or D).

4. Get Results
  Endpoint: GET /api/quiz/results
  Description: Shows the total number of questions answered and the count of correct and incorrect submissions.

5. Get a Hint for a Question
  Endpoint: GET /api/quiz/hint?questionId=1
  Parameters:
    questionId (Long): The ID of the question.


##  Installation and Setup

Clone this repository:
git clone https://github.com/your-username/quiz-app.git
cd quiz-app
1.Make sure you have Java 17 or higher installed.
2.Run the application:

bash
./mvnw spring-boot:run

3.Access the H2 database console at:
  URL: http://localhost:8080/h2-console
  JDBC URL: jdbc:h2:mem:testes
  Username: root
  Password: password

   
