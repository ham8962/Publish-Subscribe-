package org.example;

import java.util.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Broker {
    private List<String> broker;
    private final Lock lock;
    private final Condition condition;


    private Broker() {
        this.lock = new ReentrantLock();
        this.condition = lock.newCondition();
        this.broker = new ArrayList<>();
    }

    private static class BrokerInstanceHolder {
        private static final Broker instance = new Broker();
    }

    public static Broker getInstance() {
        return BrokerInstanceHolder.instance;
    }

    public void publish(String message) {
        lock.lock();
        try {
            broker.add(message);
            for (String data : broker) {
                System.out.println("message is added");
            }
            condition.signal();
        } finally {
            lock.unlock();
        }
    }

    public void receive() {
        lock.lock();
        try {
            while (broker.isEmpty()){
                condition.await();
            }
            if (!broker.isEmpty()) {
                broker.remove(0);
                System.out.println("Message is received");
            }
        } catch (InterruptedException exception) {

        } finally {
            lock.unlock();
        }
    }
}
