package com.adaptionsoft.games.uglytrivia;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class CategoriesService implements ICategoriesService {
    private static final int MAX_QUESTION_NUMBER = 50;
    private LinkedList<String> popQuestions = new LinkedList<>();
    private LinkedList<String> scienceQuestions = new LinkedList<>();
    private LinkedList<String> sportsQuestions = new LinkedList<>();
    private LinkedList<String> rockQuestions = new LinkedList<>();
    public static final String POP_CATEGORY = "Pop";
    public static final String SCIENCE_CATEGORY = "Science";
    public static final String SPORTS_CATEGORY = "Sports";
    public static final String ROCK_CATEGORY = "Rock";

    private final Map<Integer, String> questionCategories = new HashMap<>();
    {
        questionCategories.put(0, POP_CATEGORY);
        questionCategories.put(1, SCIENCE_CATEGORY);
        questionCategories.put(2, SPORTS_CATEGORY);
        questionCategories.put(3, ROCK_CATEGORY);
    }

    public void initCategoriesQuestion() {
        for (int questionNumber = 0; questionNumber < MAX_QUESTION_NUMBER; questionNumber++) {
            popQuestions.addLast((POP_CATEGORY + " Question " + questionNumber));
            scienceQuestions.addLast((SCIENCE_CATEGORY + " Question " + questionNumber));
            sportsQuestions.addLast((SPORTS_CATEGORY + " Question " + questionNumber));
            rockQuestions.addLast((ROCK_CATEGORY + " Question " + questionNumber));
        }
    }

    public void askQuestion(int playerPosition) {
        if (POP_CATEGORY.equals(getCurrentCategory(playerPosition)))
            displayMessageOnConsole(popQuestions.removeFirst());
        if (SCIENCE_CATEGORY.equals(getCurrentCategory(playerPosition)))
            displayMessageOnConsole(scienceQuestions.removeFirst());
        if (SPORTS_CATEGORY.equals(getCurrentCategory(playerPosition)))
            displayMessageOnConsole(sportsQuestions.removeFirst());
        if (ROCK_CATEGORY.equals(getCurrentCategory(playerPosition)))
            displayMessageOnConsole(rockQuestions.removeFirst());
    }

    public String getCurrentCategory(int playerPosition) {
        int numberOfCategory = questionCategories.size();
        int currentIndexOfQuestionCategory = playerPosition % numberOfCategory;
        return questionCategories.get(currentIndexOfQuestionCategory);
    }

    private void displayMessageOnConsole(String message) {
        System.out.println(message);
    }
}
