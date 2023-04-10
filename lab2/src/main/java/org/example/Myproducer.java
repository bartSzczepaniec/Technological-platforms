package org.example;
import java.util.Random;
public class Myproducer implements Runnable{
    private Myresource resource;
    private int number;
    Random randomizer;
    public Myproducer(Myresource resource, int number)
    {
        this.resource = resource;
        this.number = number;
        randomizer = new Random();
    }
    @Override
    public void run() {
            int x = randomizer.nextInt(50);
            resource.put(number);
            System.out.println("Wyprodukowano liczbe " + number);

    }

    public void setNumber(int number) {
        this.number = number;
    }
}
