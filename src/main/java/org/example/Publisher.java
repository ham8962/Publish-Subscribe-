package org.example;

import java.util.*;

public class Publisher implements Runnable {
    private static Broker broker;
    private String name;
    private int input;
    //private volatile boolean flag;
    private final FlagSettable flagSettable;

    public Publisher(String name, FlagSettable flagSettable) {
        this.name = name;
        this.flagSettable = flagSettable;
        this.broker = Broker.getInstance();
    }

    /*
    public void publishCounter() {
        Scanner sc = new Scanner(System.in);
        input = sc.nextInt();
        broker = Broker.getInstance();
        if (input == 0) {
            flag = true;
        } else {
            String message = "I'm a " + name;
            for (int i = 0; i < input; i++) {
                System.out.println("message is");
                broker.publish(message);
            }
        }
    }
    */

    @Override
    public void run() {
        Scanner inputScanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter the input");
            input = inputScanner.nextInt();
            if (input == 0) {
                System.out.println("Stop the System");
                flagSettable.setFlag(true);
                break;
            } else {
                String message = "It was published by " + name;
                for (int i = 0; i < input; i++) {
                    System.out.println(message);
                    broker.publish(message);
                }
            }

        }
    }
}

