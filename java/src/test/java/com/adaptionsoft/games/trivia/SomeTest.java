package com.adaptionsoft.games.trivia;


import com.adaptionsoft.games.uglytrivia.Game;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SomeTest {

	@Test
	public void true_is_true() {
		assertThat(true).isTrue();
	}

	@Test
	public void shouldReturn2When2PlayersAreAdded(){
		Game game = new Game();

		game.add("playerOne");
		game.add("playerTwo");

		assertThat(game.howManyPlayers()).isEqualTo(2);
	}

	@Test
	public void shouldReturnFalseWhenUserAreOne(){
		Game game = new Game();

		game.add("playerOne");

		assertThat(game.isPlayable()).isEqualTo(false);
	}

	@Test
	public void shouldReturnTrueWhenUsersAreTwo(){
		Game game = new Game();

		game.add("playerOne");
		game.add("playerTwo");

		assertThat(game.isPlayable()).isEqualTo(true);
	}
	@Test
	public void shouldReturnTrueWhenUsersAreFour(){
		Game game = new Game();

		game.add("playerOne");
		game.add("playerTwo");
		game.add("playerThree");
		game.add("playerFour");

		assertThat(game.isPlayable()).isEqualTo(true);
	}

}
