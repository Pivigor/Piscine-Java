package edu.school21.sockets.server;

import edu.school21.sockets.services.UsersService;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

@Component
public class Server {
    public Server(UsersService usersService) {
        this.usersService = usersService;
    }

    public void startListening(int port) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Ready to connect");
            Socket clientSocket = serverSocket.accept();
            clientHandler(clientSocket);
            System.out.println("Finish operation");
            clientSocket.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void clientHandler(Socket clientSocket) {
        try {
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            out.println("Hello from Server!");

            String message = in.readLine();
            switch (message) {
                case "signUp":
                    out.println("Enter username:");
                    String login = in.readLine();
                    out.println("Enter password:");
                    String password = in.readLine();
                    if (usersService.signUp(login, password)) {
                        out.println("Successful!" + "<exit>");
                    } else {
                        out.println("Error! Such username already exists." + "<exit>");
                    }
                    break;

                default:
                    out.println("Command not found" + "<exit>");
                    break;
            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private final UsersService usersService;
}
