package org.example;

import java.util.*;

public class Outputresource {
    private List<Boolean> stasks;
    private List<Boolean> tasks;
    public Outputresource()
    {
        tasks = new ArrayList<>();
        stasks = Collections.synchronizedList(tasks);
    }

    public void put(boolean value) {
        synchronized (this) {
            stasks.add(value);
            notifyAll();
        }
    }

    public List<Boolean> getStasks() {
        return stasks;
    }
}
