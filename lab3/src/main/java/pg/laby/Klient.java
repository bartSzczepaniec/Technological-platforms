package pg.laby;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Klient {
    public static void main( String[] args )
    {
        /*try (Socket client = new Socket("localhost", 9797)) {
            System.out.println("Sukces");
        } catch (IOException ex) {
            System.err.println(ex);
        }*/
       // ObjectInputStream in = null;
       // ObjectOutputStream out = null;

        try (Socket client = new Socket("localhost", 9797)) {
            try  {
                //System.out.println("asd");
                ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(client.getInputStream());

                //System.out.println("sd");
                //System.out.println("pol");
                try{
                   // System.out.println("pol");
                    String answ = in.readUTF();
                    System.out.println(answ);
                    Scanner s = new Scanner(System.in);
                    String n = s.next();
                    out.writeUTF(n);
                    out.flush();
                    String answ2 = in.readUTF();
                    System.out.println(answ2);
                    int num = Integer.parseInt(n);
                    for(int i =0;i<num;i++)
                    {
                        Message m = new Message();
                        m.setContent("asdasd");
                        m.setNumber(i);
                        out.writeObject(m);
                        out.flush();
                    }
                    out.close();
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException ex) {
            System.err.println(ex);
        }


    }
}
