package com.epam.qms.util;

public class Constants {
	private Constants() {
	}

	public static final String WELCOME_MESSAGE = "************* Welcome to the Quiz Management System *************";
	public static final String LOGIN_OPTIONS_MESSAGE = "Press: \n1 for admin login \n2 for user login";
	public static final String ENTER_USER_ID_PROMPT = "Enter User Name: ";
	public static final String ENTER_PASSWORD_PROMPT = "Enter your Password: ";
	public static final String WRONG_ID_OR_PASSWORD_MESSAGE = "Wrong Username or Password. TRY AGAIN! You have ";
	public static final String ATTEMPT_LEFT_MESSAGE = " attempt left";
	public static final String SUCCESSFUL_LOGIN_MESSAGE = "Logged In Successfully.....:)";
	public static final String ADMIN_OPTIONS_MESSAGE = "Press:\n1 -> Question Services\n2 -> Quiz Services\n3 -> Exit";
	public static final String INVALID_INPUT_MESSAGE = "Invalid input. Please enter a valid input!";
	public static final String QUIZ_OPTIONS_MESSAGE = "Press:\n1 -> Create Quiz\n2 -> Modify Quiz\n3 -> Remove Quiz\n4 -> View Quiz by Quiz ID\n5 -> View All Quizes\n6 -> Exit";
	public static final String QUESTION_OPTIONS_MESSAGE = "Press:\n1 -> Add Question\n2 -> Modify Question\n3 -> Remove Question\n4 -> View Question By Id\n5 -> View All Questions\n6 -> Exit";
	public static final String NUMBER_OF_QUESTIONS_PROMPT = "Enter Number of Questions you want to Add (Integer): ";
	public static final String QUESTION_TO_MODIFY_PROMPT = "Enter the question Id that you want to modify: ";
	public static final String QUESTION_TO_REMOVE_PROMPT = "Enter the question Id that you want to remove: ";
	public static final String QUESTION_TO_VIEW_PROMPT = "Enter question Id to view question: ";
	public static final String ENTER_QUESTION_PROMPT = "Enter new question: ";
	public static final String ENTER_NUMBER_OF_OPTION_PROMPT = "Enter number of options: ";
	public static final String DIFFICULTY_LEVEL_PROMPT = "Enter difficulty level (Easy, Moderate or Hard) for the question: ";
	public static final String QUESTION_TAG_PROMPT = "Enter the question tags: ";
	public static final String ANSWER_OF_QUESTION_PROMPT = "Which option is the answer for the question: ";
	public static final String ENTER_MARKS_PROMPT = "Enter the marks for the question (only 2 or 4): ";
	public static final String ENTER_OPTION_PROMPT = "Enter option ";
	public static final String QUESTION_MODIFIED_SUCCESSFULLY_MESSAGE = "Question Modified Successfully";
	public static final String ERROR_MODIFYING_QUESTION_MESSAGE = "Error in Modifying Question";
	public static final String QUESTION_REMOVED_SUCCESSFULLY_MESSAGE = "Question removed Successfully";
	public static final String ERROR_MODIFYING_QUIZ = "Error in modifying Quiz";
	public static final String QUIZ_ID_PROMPT = "Enter the Quiz : ";
	public static final String NO_QUIZ_FOUND_MESSAGE = "No Quiz Found!";
	public static final String QUIZ_TO_MODIFY_PROMPT = "Enter the Quiz Id you want to Modify: ";
	public static final String MODIFY_QUIZ_OPTIONS_MESSAGE = "Press:\n1 -> Add Question to Quiz\n2 -> Remove Question from Quiz";
	public static final String QUIZ_TO_REMOVE_PROMPT = "Enter the Quiz Id which you want to remove: ";
	public static final String QUIZ_CREATED_SUCCESSFULLY_MESSAGE = "Quiz Created Successfully";
	public static final String ERROR_QUIZ_CREATION_MESSAGE = "Error in creating Quiz! TRY AGAIN......";
	public static final String MAXIMUM_MARKS_PROMPT = "Enter the Maximum marks of Quiz: ";
	public static final String ADD_IN_QUIZ_OPTION_MESSAGE = "1 -> Add New Question\n2 -> Add Existing Question\n3 -> Exit";
	public static final String QUESTION_ID_TO_ADD_PROMPT = "Enter the question Id that you want to add (Please Enter all the Question Id's in single line separated by space): ";
	public static final String QUESTION_IN_LIBRARY_MESSAGE = "Following questions are present in Library: ";
	public static final String QUIZ_REMOVED_MESSAGE = "Quiz removed Successfully";
	public static final String ERROR_REMOVING_QUIZ_MESSAGE = "Error in removing Quiz";
	public static final String QUESTION_ID_TO_REMOVE_PROMPT = "Enter Question Id you want to Remove (Please Enter all Question Id's in single line separated by space): ";
	public static final String QUESTION_NOT_FOUND_MESSAGE = "Question not found.";
	public static final String LINE_SEPARATOR = "===================================================================";
	public static final String NO_QUESTION_MESSAGE = "No Question Present in DataBase";
	public static final String QUESTION_ADDED_SUCCESSFULLY_MESSAGE = "Question Added Successfully!";
	public static final String INVALID_ID_FORMAT_MESSAGE = "Please enter IDs in valid format!";
	public static final String NOT_LOGIN = "Not Login";
	public static final String USER_OPTION_MESSAGE = "Press: \n1 -> Login \n2 -> Register \n3 -> Exit";
	public static final String ENTER_QUIZ_TITLE_PROMPT = "Enter Quiz Title : ";
	public static final String INVALID_QUESTION_ID = "Invalid Question ID";
	public static final String INVALID_QUIZ_ID = "Invalid Quiz ID";
	public static final String ERROR_ADDING_QUESTION_IN_QUIZ = "Error in Adding Question in Quiz";
	public static final String ERROR_REMOVING_QUESTION_IN_QUIZ = "Error in Removing Question in Quiz";
	public static final String DATABASE_NOT_WORKING = "Database Not Working";
	public static final String QUESTION_ID = "Question ID = {}";
	public static final String QUIZ_ID = "Quiz ID = {}";
	public static final String USER_NAME_NOT_AVAILABLE = "User name not available";
	public static final String USER_ALREADY_EXISTS = "User name already exists";
	public static final String NO_VALID_USER = "No valid user found";
	public static final String INVALID_QUIZ_TITLE = "Quiz title already exist";
}
