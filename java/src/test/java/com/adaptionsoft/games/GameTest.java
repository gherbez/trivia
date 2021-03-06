package com.adaptionsoft.games;

import com.adaptionsoft.games.uglytrivia.AnotherGame;
import com.adaptionsoft.games.uglytrivia.Game;
import com.adaptionsoft.games.uglytrivia.MessageInterface;
import org.junit.Ignore;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;


public class GameTest {

    @Test
    public void shouldPrintAnswerWasCorrect(){
        //Given
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        System.setOut(ps);
        String expected = "Answer was correct!!!!";

        ArrayList players = new ArrayList();
        players.add("Toto");
        Game game = new Game(players);

        //When
        game.wasCorrectlyAnswered();

        //Then
        assertThat(baos.toString()).contains(expected);
    }

    @Test
    public void testwasCorrectlyAnsweredMethod(){
        //given
        ArrayList players = new ArrayList();
        players.add("Toto");
        Game game = new Game(players);

        //When

        //then
        for(int i =0; i < 5; i++){
            assertThat(game.wasCorrectlyAnswered()).isEqualTo(true);
        }
    }


    @Test
    public void shouldAddNewPlayerAndDisplayItsName(){
        AnotherGame anotherGame = new AnotherGame();

        anotherGame.addPlayer("toto");

        assertThat(anotherGame.getConsoleOutPut().toString()).isEqualTo("toto was added They are player number 1 ");
    }

    @Test
    @Ignore
    public void shouldAddNewPlayerAndDisplayItsName2(){
        MessageInterface messageInterface = mock(MessageInterface.class);
//        Game game = new Game(messageInterface);
//        game.add("Toto");
        verify(messageInterface).display("Toto was added");
    }
}
