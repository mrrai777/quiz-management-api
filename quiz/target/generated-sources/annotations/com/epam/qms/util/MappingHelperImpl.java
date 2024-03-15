package com.epam.qms.util;

import com.epam.qms.dto.QuestionDTO;
import com.epam.qms.dto.QuestionDTO.QuestionDTOBuilder;
import com.epam.qms.dto.QuizDTO;
import com.epam.qms.dto.QuizDTO.QuizDTOBuilder;
import com.epam.qms.dto.UserDTO;
import com.epam.qms.dto.UserDTO.UserDTOBuilder;
import com.epam.qms.entity.Question;
import com.epam.qms.entity.Quiz;
import com.epam.qms.entity.Role;
import com.epam.qms.entity.User;
import com.epam.qms.entity.User.UserBuilder;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-15T15:36:42+0530",
    comments = "version: 1.3.1.Final, compiler: Eclipse JDT (IDE) 3.36.0.v20231114-0937, environment: Java 17.0.9 (Eclipse Adoptium)"
)
@Component
public class MappingHelperImpl implements MappingHelper {

    @Override
    public Question convertToQuestion(QuestionDTO questionDto) {
        if ( questionDto == null ) {
            return null;
        }

        Question question = new Question();

        question.setQuestionId( questionDto.getQuestionId() );
        question.setDescription( questionDto.getDescription() );
        List<String> list = questionDto.getOptions();
        if ( list != null ) {
            question.setOptions( new ArrayList<String>( list ) );
        }
        question.setDifficulty( questionDto.getDifficulty() );
        question.setTopic( questionDto.getTopic() );
        question.setAnswer( questionDto.getAnswer() );
        if ( questionDto.getMarks() != null ) {
            question.setMarks( questionDto.getMarks() );
        }

        return question;
    }

    @Override
    public QuestionDTO convertToQuestionDTO(Question question) {
        if ( question == null ) {
            return null;
        }

        QuestionDTOBuilder questionDTO = QuestionDTO.builder();

        questionDTO.answer( question.getAnswer() );
        questionDTO.description( question.getDescription() );
        questionDTO.difficulty( question.getDifficulty() );
        questionDTO.marks( question.getMarks() );
        List<String> list = question.getOptions();
        if ( list != null ) {
            questionDTO.options( new ArrayList<String>( list ) );
        }
        questionDTO.questionId( question.getQuestionId() );
        questionDTO.topic( question.getTopic() );

        return questionDTO.build();
    }

    @Override
    public Quiz convertToQuiz(QuizDTO quizDto) {
        if ( quizDto == null ) {
            return null;
        }

        Quiz quiz = new Quiz();

        quiz.setTitle( quizDto.getTitle() );
        quiz.setQuestions( convertToQuestions( quizDto.getQuestions() ) );
        quiz.setMarks( quizDto.getMarks() );
        quiz.setQuizId( quizDto.getQuizId() );

        return quiz;
    }

    @Override
    public QuizDTO convertToQuizDTO(Quiz quiz) {
        if ( quiz == null ) {
            return null;
        }

        QuizDTOBuilder quizDTO = QuizDTO.builder();

        quizDTO.marks( quiz.getMarks() );
        quizDTO.questions( convertToQuestionDTOs( quiz.getQuestions() ) );
        quizDTO.quizId( quiz.getQuizId() );
        quizDTO.title( quiz.getTitle() );

        return quizDTO.build();
    }

    @Override
    public UserDTO convertToUserDTO(User user) {
        if ( user == null ) {
            return null;
        }

        UserDTOBuilder userDTO = UserDTO.builder();

        userDTO.password( user.getPassword() );
        List<Role> list = user.getRoles();
        if ( list != null ) {
            userDTO.roles( new ArrayList<Role>( list ) );
        }
        userDTO.userId( user.getUserId() );
        userDTO.username( user.getUsername() );

        return userDTO.build();
    }

    @Override
    public User convertToUser(UserDTO user) {
        if ( user == null ) {
            return null;
        }

        UserBuilder user1 = User.builder();

        user1.password( user.getPassword() );
        List<Role> list = user.getRoles();
        if ( list != null ) {
            user1.roles( new ArrayList<Role>( list ) );
        }
        user1.userId( user.getUserId() );
        user1.username( user.getUsername() );

        return user1.build();
    }

    @Override
    public List<QuestionDTO> convertToQuestionDTOs(List<Question> questions) {
        if ( questions == null ) {
            return null;
        }

        List<QuestionDTO> list = new ArrayList<QuestionDTO>( questions.size() );
        for ( Question question : questions ) {
            list.add( convertToQuestionDTO( question ) );
        }

        return list;
    }

    @Override
    public List<Question> convertToQuestions(List<QuestionDTO> questionDtos) {
        if ( questionDtos == null ) {
            return null;
        }

        List<Question> list = new ArrayList<Question>( questionDtos.size() );
        for ( QuestionDTO questionDTO : questionDtos ) {
            list.add( convertToQuestion( questionDTO ) );
        }

        return list;
    }
}
