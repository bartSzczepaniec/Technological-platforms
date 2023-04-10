package pg.laby;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class KlientHandle implements Runnable {

    private final Socket clientS;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    public KlientHandle(Socket clientS, ObjectOutputStream out, ObjectInputStream in){
        this.clientS = clientS;
        this.in = in;
        this.out = out;
    }
    @Override
    public void run() {
        String ready = "ready";
        String readyfm = "ready for messages";
        byte[] readyb = ready.getBytes(StandardCharsets.UTF_8);
        byte[] readyfmb = readyfm.getBytes(StandardCharsets.UTF_8);
System.out.println("d");
        try {
          //  in = new DataInputStream(clientS.getInputStream());
          //  out = new DataOutputStream(clientS.getOutputStream());
            out.writeUTF("ready");
            out.flush();
            Integer n;
           while(true)
           {
               try{
                   String t = in.readUTF();
                   n = Integer.parseInt(t);
                   System.out.println(n);
                   out.writeUTF("ready for messages");
                   out.flush();
                   for(int i = 0 ;i<n;i++)
                   {
                       Message x = (Message) in.readObject();
                       System.out.println(x.getNumber());
                   }
                   break;
               }
               catch (EOFException e){
               } catch (ClassNotFoundException e) {
                   e.printStackTrace();
               }
           }
            //System.out.println("poszlo");
            out.close();
            in.close();
            clientS.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                    clientS.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
