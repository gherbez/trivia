package com.adaptionsoft.games.uglytrivia;

import java.util.*;

public class Game {

    public static final int MAX_NUMBER_PURSES = 6;
    private ICategoriesService questionService;

    private static final int MAX_NUMBER_CASES = 11;
    private List<String> players = new ArrayList<>();
    private int[] places = new int[MAX_NUMBER_PURSES];
    private int[] purses = new int[MAX_NUMBER_PURSES];
    private boolean[] inPenaltyBox = new boolean[MAX_NUMBER_PURSES];
    private int currentPlayer = 0;
    private boolean isGettingOutOfPenaltyBox;


    Category pop = new Category("Pop");

    public Game(){

    }

    public Game(List<String> players) {
        this();
        this.players = players;
    }

    public Game(ICategoriesService questionService) {
        this.questionService = questionService;
        this.questionService.initCategoriesQuestion();
    }

    public boolean addPlayer(String playerName) {
        players.add(playerName);
        places[howManyPlayers()] = 0;
        purses[howManyPlayers()] = 0;
        inPenaltyBox[howManyPlayers()] = false;

        displayMessageOnConsole(playerName + " was added");
        displayMessageOnConsole("They are player number " + players.size());
        return true;
    }

    public void roll(int diceResult) {
        displayMessageOnConsole(players.get(currentPlayer) + " is the current player");
        displayMessageOnConsole("They have rolled a " + diceResult);

        if (inPenaltyBox[currentPlayer]) {
            if (diceResult % 2 != 0) {
                isGettingOutOfPenaltyBox = true;

                displayMessageOnConsole(players.get(currentPlayer) + " is getting out of the penalty box");
                movePlayer(diceResult);
                questionService.askQuestion(places[currentPlayer]);
            } else {
                isGettingOutOfPenaltyBox = false;
                displayMessageOnConsole(players.get(currentPlayer) + " is not getting out of the penalty box");
            }

        } else {
            movePlayer(diceResult);
            questionService.askQuestion(places[currentPlayer]);
        }

    }

    public boolean wasCorrectlyAnswered() {
        if (inPenaltyBox[currentPlayer]) {
            if (isGettingOutOfPenaltyBox) {
                displayMessageOnConsole("Answer was correct!!!!");
                purses[currentPlayer]++;
                displayMessageOnConsole(players.get(currentPlayer)
                        + " now has "
                        + purses[currentPlayer]
                        + " Gold Coins.");

                boolean hasWin = hasCurrentPlayerCompletedHisPurses();
                defineNextPlayer();

                return hasWin;
            } else {
                defineNextPlayer();
                return true;
            }
        } else {
            displayMessageOnConsole("Answer was correct!!!!");
            purses[currentPlayer]++;
            displayMessageOnConsole(players.get(currentPlayer)
                    + " now has "
                    + purses[currentPlayer]
                    + " Gold Coins.");

            boolean hasWin = hasCurrentPlayerCompletedHisPurses();
            defineNextPlayer();

            return hasWin;
        }
    }

    public boolean wrongAnswer() {
        displayMessageOnConsole("Question was incorrectly answered");
        displayMessageOnConsole(players.get(currentPlayer) + " was sent to the penalty box");
        inPenaltyBox[currentPlayer] = true;

        defineNextPlayer();
        return true;
    }

    protected void displayMessageOnConsole(String message) {
        System.out.println(message);
    }

    private void movePlayer(int diceResult) {
        places[currentPlayer] += diceResult;
        if (places[currentPlayer] > MAX_NUMBER_CASES){
            places[currentPlayer] -= 12;
        }

        displayMessageOnConsole(players.get(currentPlayer) + "'s new location is " + places[currentPlayer]);
        displayMessageOnConsole("The category is " + questionService.getCurrentCategory(places[currentPlayer]));
    }


    private void defineNextPlayer() {
        currentPlayer++;
        if (currentPlayer == players.size()){
            currentPlayer = 0;
        }
    }

    private int howManyPlayers() {
        return players.size();
    }

    private boolean hasCurrentPlayerCompletedHisPurses() {
        return purses[currentPlayer] != MAX_NUMBER_PURSES;
    }
}
