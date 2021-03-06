
package com.adaptionsoft.games.runner;
import java.util.Random;

import com.adaptionsoft.games.uglytrivia.CategoriesService;
import com.adaptionsoft.games.uglytrivia.Game;


public class GameRunner {

	private static boolean notAWinner;

	public static Random rand = new Random();
	public static boolean isTest = false;

	public static void main(String[] args) {
		Game aGame = new Game(new CategoriesService());
		
		aGame.addPlayer("Chet");
		aGame.addPlayer("Pat");
		aGame.addPlayer("Sue");

		if(isTest){
			rand = new Random(Integer.parseInt(args[0]));
		}

		do {
			
			aGame.roll(rand.nextInt(5) + 1);
			
			if (rand.nextInt(9) == 7) {
				notAWinner = aGame.wrongAnswer();
			} else {
				notAWinner = aGame.wasCorrectlyAnswered();
			}
			
			
			
		} while (notAWinner);
		
	}
}
