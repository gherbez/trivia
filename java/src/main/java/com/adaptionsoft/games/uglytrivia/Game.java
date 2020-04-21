package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;
import java.util.LinkedList;

public class Game {

	private MessageInterface messageImpl;

	public boolean add(String playerName) {
		players.add(playerName);
		places[howManyPlayers()] = 0;
		purses[howManyPlayers()] = 0;
		inPenaltyBox[howManyPlayers()] = false;

		displayMessageOnConsole(playerName + " was added");
		displayMessageOnConsole("They are player number " + players.size());
		return true;
	}

	protected void displayMessageOnConsole(String message) {
		messageImpl.display(message);
	}


	ArrayList players = new ArrayList();
	int[] places = new int[6];

	int[] purses  = new int[6];
	boolean[] inPenaltyBox  = new boolean[6];
	private LinkedList popQuestions = new LinkedList();
	private LinkedList scienceQuestions = new LinkedList();

	private LinkedList sportsQuestions = new LinkedList();
	private LinkedList rockQuestions = new LinkedList();

	int currentPlayer = 0;

    boolean isGettingOutOfPenaltyBox;

	public Game(ArrayList players) {
		this();
		this.players = players;
	}

	public Game(MessageInterface messageInterface) {
		this.messageImpl = messageInterface;
	}

	public  Game(){
    	for (int i = 0; i < 50; i++) {
			popQuestions.addLast("Pop Question " + i);
			scienceQuestions.addLast(("Science Question " + i));
			sportsQuestions.addLast(("Sports Question " + i));
			rockQuestions.addLast(("Rock Question " + i));
    	}
    }
//	public boolean isPlayable() {

//		return (howManyPlayers() >= 2);

//	}

	public int howManyPlayers() {
		return players.size();
	}

	public void roll(int diceResult) {
		System.out.println(players.get(currentPlayer) + " is the current player");
		System.out.println("They have rolled a " + diceResult);

		if (inPenaltyBox[currentPlayer]) {
			if (diceResult % 2 != 0) {
				isGettingOutOfPenaltyBox = true;

				System.out.println(players.get(currentPlayer) + " is getting out of the penalty box");
				deplacementPlayer(diceResult);
				askQuestion();
			} else {
				isGettingOutOfPenaltyBox = false;
				System.out.println(players.get(currentPlayer) + " is not getting out of the penalty box");
				}

		} else {
			deplacementPlayer(diceResult);
			askQuestion();
		}

	}

	private void deplacementPlayer(int diceResult) {
		places[currentPlayer] = places[currentPlayer] + diceResult;
		if (places[currentPlayer] > 11) places[currentPlayer] = places[currentPlayer] - 12;

		System.out.println(players.get(currentPlayer)
				+ "'s new location is "
				+ places[currentPlayer]);
		System.out.println("The category is " + currentCategory());
	}

	
	private String currentCategory() {
		String questionCategory = "Rock";

		if((places[currentPlayer] % 4 == 0)){
			questionCategory = "Pop";
		} else if((places[currentPlayer] % 4 == 1)){
			questionCategory = "Science";
		} else if((places[currentPlayer] % 4 == 2)){
			questionCategory = "Sports";
		}
		return questionCategory;
	}

	public boolean wasCorrectlyAnswered() {
		if (inPenaltyBox[currentPlayer]){
			if (isGettingOutOfPenaltyBox) {
				System.out.println("Answer was correct!!!!");
				purses[currentPlayer]++;
				System.out.println(players.get(currentPlayer) 
						+ " now has "
						+ purses[currentPlayer]
						+ " Gold Coins.");
				
				boolean winner = didPlayerWin();
				defineNextPlayer();

				return winner;
			} else {
				defineNextPlayer();
				return true;
			}
			
			



		} else {
		
			System.out.println("Answer was correct!!!!");
			purses[currentPlayer]++;
			System.out.println(players.get(currentPlayer) 
					+ " now has "
					+ purses[currentPlayer]
					+ " Gold Coins.");
			
			boolean winner = didPlayerWin();
			defineNextPlayer();

			return winner;
		}
	}
	
	public boolean wrongAnswer(){
		System.out.println("Question was incorrectly answered");
		System.out.println(players.get(currentPlayer)+ " was sent to the penalty box");
		inPenaltyBox[currentPlayer] = true;

		defineNextPlayer();
		return true;
	}

	private void defineNextPlayer() {
		currentPlayer++;
		if (currentPlayer == players.size()) currentPlayer = 0;
	}


	private void askQuestion() {
		if (currentCategory().equals("Pop"))
			System.out.println(popQuestions.removeFirst());
		if (currentCategory().equals("Science"))
			System.out.println(scienceQuestions.removeFirst());
		if (currentCategory().equals("Sports"))
			System.out.println(sportsQuestions.removeFirst());
		if (currentCategory().equals("Rock"))
			System.out.println(rockQuestions.removeFirst());
	}

	private boolean didPlayerWin() {
		return !(purses[currentPlayer] == 6);
	}
}
