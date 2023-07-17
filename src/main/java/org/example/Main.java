package org.example;

public class Main {
    public static void main(String[] args) throws Exception {
        FlagController flagController = new FlagController();

        Publisher pub = new Publisher("pub1", flagController);
        User user = new User("sub1", flagController);

        Thread publisher1 = new Thread(pub);
        Thread user1 = new Thread(user);


        publisher1.start();
        user1.start();

        publisher1.join();
        user1.join();



        System.out.println("FINISH");
    }
}