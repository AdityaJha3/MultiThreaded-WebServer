package MultiThreaded;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Consumer;

public class Server {

    public Consumer<Socket> getConsumer(){
        return (clientSocket)->{
            try {
                BufferedReader fromClient =new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                PrintWriter toClient =new PrintWriter(clientSocket.getOutputStream(), true);

                // 1. Pehle suno
                String msg = fromClient.readLine();
                System.out.println("Client said: " + msg);

                // 2. Phir bolo
                toClient.println("Reply from server at port " + clientSocket.getLocalPort());
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
    }

    public static void main(String[] args) {
        int port =8080;
        Server server=new Server();
        try {
            ServerSocket socket=new ServerSocket(port);
            // socket.setSoTimeout(20000);
            System.out.println("Server listening on " + socket.getLocalPort());
            while (true) { 
                Socket acceptedSocket=socket.accept();
                Thread thread=new Thread(()->server.getConsumer().accept(acceptedSocket));
                thread.start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
