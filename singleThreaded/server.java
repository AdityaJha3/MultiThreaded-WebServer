package singleThreaded;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
public class server{
    public void run(){
        int port=8080;  
        System.out.println("Server is running...");
         try {
            ServerSocket socket = new ServerSocket(port);
            System.out.println("Server is running at "+ socket.getLocalPort());
            socket.setSoTimeout(100000);

            while (true) {
                try {
                    Socket acceptedSocket = socket.accept();
                    System.out.println("A client connected." + acceptedSocket.getRemoteSocketAddress());

                    // Handle the client connection in a separate thread or method here
                    PrintWriter toClient=new PrintWriter(acceptedSocket.getOutputStream());
                    BufferedReader fromClient=new BufferedReader(new InputStreamReader(acceptedSocket.getInputStream()));
                    // 1. Pehle client ka message padho
                    String msg = fromClient.readLine();
                    System.out.println("Client said: " + msg);

                    // 2. Phir reply bhejo
                    toClient.println("Hello from server at "+ acceptedSocket.getLocalSocketAddress());


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        } catch (IOException e) {
            System.out.println("Could not start server on port " + port);
            e.printStackTrace();
        }
    }
    public static void main(String args[]){
        System.out.println("This is the server class.");
        server server=new server();
        try{
            server.run();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}