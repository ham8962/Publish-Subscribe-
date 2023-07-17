package org.example;

public class User implements Runnable {
    private static Broker broker;

    private final String  userName;
    private FlagNotifier flagNotifier;

    public User(String name , FlagNotifier flagNotifier) {
        this.userName = name;
        this.flagNotifier = flagNotifier;
        this.broker = Broker.getInstance();
    }

    @Override
    public void run() {
        while (!flagNotifier.getFlag()) {
            broker.receive();
        }
        /*
        try {
            Thread.sleep(500);
        }catch (InterruptedException e){
            e.printStackTrace();
        }*/
        System.out.println("FINISH");
    }
}


