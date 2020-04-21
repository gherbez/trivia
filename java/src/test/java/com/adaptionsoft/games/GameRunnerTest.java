package com.adaptionsoft.games;

import com.adaptionsoft.games.runner.GameRunner;
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
        PrintStream printStream = new PrintStream("stackTrace.txt");
        System.setOut(printStream);
        GameRunner.isTest = true;
        GameRunner.main(null);

        String expectedString = new String(readAllBytes(Paths.get("expectedResult.txt")), StandardCharsets.UTF_8);
        String generateString = new String(readAllBytes(Paths.get("stackTrace.txt")), StandardCharsets.UTF_8);
        assertThat(expectedString).isEqualTo(generateString);

    }
}