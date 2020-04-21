package com.adaptionsoft.games;

import com.adaptionsoft.games.runner.GameRunner;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;

import static java.nio.file.Files.readAllBytes;
import static org.assertj.core.api.Assertions.assertThat;


public class GameRunnerTest {

    @Test
    public void goldenMaster() throws IOException {
        GameRunner.isTest = true;

        for(int i=0; i<100; i++){
            PrintStream printStream = new PrintStream("stackTraces/stackTrace"+ i + ".txt");
            System.setOut(printStream);
            String[] args = new String[1];
            args[0]=i+"";
            GameRunner.main(args);

            String expectedString = new String(readAllBytes(Paths.get("expectedResults/expectedResult"+ i +".txt")), StandardCharsets.UTF_8);
            String generateString = new String(readAllBytes(Paths.get("stackTraces/stackTrace"+ i +".txt")), StandardCharsets.UTF_8);
            assertThat(expectedString).isEqualTo(generateString);
        }
    }

    @Test
    @Ignore
    public void goldenMasterInit() throws IOException {
        GameRunner.isTest = true;

        for(int i=0; i<100; i++){
            PrintStream printStream = new PrintStream("expectedResults/expectedResult"+ i + ".txt");
            System.setOut(printStream);
            String[] args = new String[1];
            args[0]=i+"";
            GameRunner.main(args);
        }
    }
}