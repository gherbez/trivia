package com.adaptionsoft.games.uglytrivia;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;


public class GameTest {

    @Test
    public void createRockQuestion() {
    }

    @Test
    public void isPlayable() {
    }

    @Test
    public void add() {
    }

    @Test
    public void howManyPlayers() {
    }

    @Test
    public void roll() {
    }

    @Test
    public void wasCorrectlyAnswered() {
    }

    @Test
    public void wrongAnswer() {
    }

    @Test
    public void shouldReturn2When2PlayersAreAdded() {
        Game game = new Game();

        game.add("playerOne");
        game.add("playerTwo");

        assertThat(game.howManyPlayers()).isEqualTo(2);
    }

    @Test
    public void shouldReturnFalseWhenUserAreOne() {
        Game game = new Game();

        game.add("playerOne");

        assertThat(game.isPlayable()).isEqualTo(false);
    }

    @Test
    public void shouldReturnTrueWhenUsersAreTwo() {
        Game game = new Game();

        game.add("playerOne");
        game.add("playerTwo");

        assertThat(game.isPlayable()).isEqualTo(true);
    }

    @Test
    public void shouldReturnTrueWhenUsersAreFour() {
        Game game = new Game();

        game.add("playerOne");
        game.add("playerTwo");
        game.add("playerThree");
        game.add("playerFour");

        assertThat(game.isPlayable()).isEqualTo(true);

    }


    @Test
    public void rollMethod(){
        Game game = new Game();

        game.roll(20);


    }

}
