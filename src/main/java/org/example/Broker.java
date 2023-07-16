package org.example;

import java.util.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Broker {
    private List<String> broker;
    private final Lock lock;
    private final Condition condition;
    private boolean threadFlag;

    private Broker(){
        this.lock = new ReentrantLock();
        this.condition = lock.newCondition();
        this.broker = new ArrayList<>();
        this.threadFlag = true;
    }

    private static class BrokerInstanceHolder {
        private static final Broker instance = new Broker();
    }

    public static Broker getInstance(){
        return BrokerInstanceHolder.instance;
    }

    public void publish(String message) {
        lock.lock();
        try {
            broker.add(message);
            if (!threadFlag){
                broker.add(message);
                condition.signal();
                threadFlag = true;
            }
        } finally {
            lock.unlock();
        }
    }

    public String recive() {
        lock.lock();
        try {
            if (broker.isEmpty()) {
                condition.await();
                threadFlag = false;
            }
            System.out.println("Is it?");
            return broker.remove(0);
        } catch(InterruptedException exception){

        }
         finally {
            lock.unlock();
        }
        return null;
    }
}
