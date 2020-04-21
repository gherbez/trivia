package com.adaptionsoft.games.uglytrivia;


public class ConsoleMessage implements MessageInterface {

    @Override
    public void display(String message) {
        System.out.println(message);
    }
}
