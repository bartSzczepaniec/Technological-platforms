package org.example;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.List;
/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        Myresource res = new Myresource();
        Outputresource ores = new Outputresource();

        int l = args.length;
        if(l == 0)
            return;
        int amount = Integer.parseInt(args[0]);
        Thread[] threads = new Thread[amount];
        Myconsumer[] consumers = new Myconsumer[amount];
        for(int i=0;i<amount;i++)
        {
            consumers[i] = new Myconsumer(res,i,ores);
            threads[i] = new Thread(consumers[i]);
        }
        Myproducer p1 = new Myproducer(res,23);
        //Myconsumer c1 = new Myconsumer(res,1, ores);
        //Myconsumer c2 = new Myconsumer(res,2, ores);
        Thread thread = new Thread(p1);
        //Thread thread2 = new Thread(c1);
        //Thread thread3 = new Thread(c2);
        //thread.start();
        thread.run();
        p1.setNumber(7);
        p1.run();
        p1.setNumber(8932312);
        p1.run();
        p1.setNumber(12);
        p1.run();


        for(int i=0;i<amount;i++)
        {
            threads[i].start();
        }
     //   thread.join();
       // thread2.join();
        //thread3.join();
        while(true)
        {
            String command = scanner.nextLine();
            //char d = scanner.next().
            if (command.equals("exit"))
            {
                thread.interrupt();
                for(int i=0;i<amount;i++)
                {
                    threads[i].interrupt();
                }
                System.out.println("Koniec, wyniki obliczen:");
                List output = ores.getStasks();
                System.out.println(output);
                break;
            }
            else if (command.equals("t"))
            {
                try
                {
                    int a = scanner.nextInt();
                    p1.setNumber(a);
                    p1.run();
                } catch (InputMismatchException e){}
            }
        }


    }
}
