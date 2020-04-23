package com.adaptionsoft.games.uglytrivia;

public interface ICategoriesService {
    void initCategoriesQuestion();
    void askQuestion(int playerPosition);
    String getCurrentCategory(int playerPosition);
}
