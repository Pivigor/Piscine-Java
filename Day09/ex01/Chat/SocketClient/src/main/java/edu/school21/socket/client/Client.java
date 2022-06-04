package edu.school21.socket.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void startConnection(int serverPort) {
        try (Socket clientSocket = new Socket("localhost", serverPort)) {
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            Scanner scanner = new Scanner(System.in);

            String answer;
            while ((answer = in.readLine()) != null) {
                if (answer.contains("<exit>")) {
                    System.out.println(answer.replace("<exit>", ""));
                    clientSocket.close();
                    return;
                } else if (answer.contains("<input>")) {
                    System.out.println(answer.replace("<input>", ""));
                    System.out.print("> ");
                    out.println(scanner.nextLine());
                } else {
                    System.out.println(answer);
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
