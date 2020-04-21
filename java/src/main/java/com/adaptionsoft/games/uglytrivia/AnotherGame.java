package com.adaptionsoft.games.uglytrivia;

public class AnotherGame extends Game {

    private StringBuilder consoleOutPut = new StringBuilder();

    @Override
    protected void displayMessageOnConsole(String message) {
        this.consoleOutPut.append(message).append(" ");
    }

    public StringBuilder getConsoleOutPut() {
        return consoleOutPut;
    }
}
