package org.example;

public class Myconsumer implements Runnable{
    private Myresource resource;
    private Outputresource outputresource;
    int id;
    public Myconsumer(Myresource resource, int id, Outputresource outputresource)
    {
        this.resource = resource;
        this.id = id;
        this.outputresource = outputresource;
    }
    @Override
    public void run() {
        while(!Thread.interrupted()) {
            try {
                int numberToCheck = resource.take();
                boolean isPrime=true;
                if(numberToCheck == 0 || numberToCheck == 1)
                    isPrime=false;
                else{
                    for(int i=2;i<numberToCheck/2;i++)
                    {
                        if(numberToCheck%i==0)
                        {
                            isPrime=false;
                            break;
                        }
                    }
                }
                if(isPrime)
                    System.out.println("Consumer"+this.id + ": " + numberToCheck + " is a prime number");
                else
                    System.out.println("Consumer"+this.id + ": " + numberToCheck + " is NOT a prime number");
                outputresource.put(isPrime);
                System.out.println(outputresource.getStasks());
                Thread.sleep(5000);
                //System.out.println(numberToCheck);
            } catch (InterruptedException ex) {
                break;
            }
        }
    }
}
