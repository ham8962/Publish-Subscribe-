package org.example;

import java.util.*;

public class Publisher extends Thread {
    private static Broker broker;
    private String name;
    private int input;
    private boolean flag;

    public Publisher(String name){
        this.name = name;
        this.flag = false;
    }

    public void publishCounter() {
        Scanner sc = new Scanner(System.in);
        input = sc.nextInt();
        broker = Broker.getInstance();
        if (input == 0) {
            flag = true;
        }else{
            String message = "I'm a " + name;
            for(int i = 0; i < input; i++){
                System.out.println("message is");
                broker.publish(message);
            }
        }
    }


    public boolean flagSignal() {
        return flag;
    }


    @Override
    public void run() {
        while (!flag) {
            publishCounter();
        }
    }
}

