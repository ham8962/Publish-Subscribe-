package org.example;

public class User extends Thread {
    private static Broker broker;
    //private Publisher publisher = new Publisher();
    private String userName;

    public User(String name) {
        this.userName = name;
        this.broker = Broker.getInstance();
    }

    @Override
    public void run() {
        while (true) {
            broker.recive();
        }
    }
}


