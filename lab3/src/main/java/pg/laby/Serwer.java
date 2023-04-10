package pg.laby;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Serwer {
    public static void main( String[] args )
    {
        List<Thread> threads = new ArrayList<>();
        List<KlientHandle> th = new ArrayList<>();
        int size =0;
        ServerSocket server = null;
        try {
            server = new ServerSocket(9797);
        } catch (IOException e) {
            e.printStackTrace();
        }
        while(true) {
            try {
                Socket socket = server.accept();
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                KlientHandle clsocket = new KlientHandle(socket, out, in);
                //th.add(clsocket);
                Thread t = new Thread(clsocket);
                t.run();
            }
            catch (IOException ex) {
                System.err.println(ex);
            }
        }
    }

}
