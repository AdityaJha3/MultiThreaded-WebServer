package singleThreaded;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class client {

    public void run() throws UnknownHostException, IOException {

        int port = 8080;

        InetAddress address = InetAddress.getByName("localhost");

        Socket socket = new Socket(address, port);

        PrintWriter toSocket =
            new PrintWriter(socket.getOutputStream(), true);

        toSocket.println("This server is from "
                + socket.getLocalSocketAddress());

        BufferedReader fromSocket =
            new BufferedReader(
                new InputStreamReader(socket.getInputStream())
            );

        String response = fromSocket.readLine();

        System.out.println("This server says " + response);

        toSocket.close();
        fromSocket.close();
        socket.close();
    }

    public static void main(String[] args) {
        try {
            client client = new client();
            client.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
