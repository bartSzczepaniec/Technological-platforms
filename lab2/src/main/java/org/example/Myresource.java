package org.example;

import java.util.*;

public class Myresource {
    private List<Integer> stasks;
   // private List<Integer> tasks;
    public Myresource()
    {
        List <Integer> tasks = new ArrayList<>();
        stasks = Collections.synchronizedList(tasks);
    }
    public synchronized Integer take() throws InterruptedException {
        while(stasks.isEmpty())
        {
            wait();
        }
        int task = stasks.get(0);
        stasks.remove(0);
        return task;
    }

    public synchronized void put(int value) {
        stasks.add(value);
        notifyAll();
    }
}
