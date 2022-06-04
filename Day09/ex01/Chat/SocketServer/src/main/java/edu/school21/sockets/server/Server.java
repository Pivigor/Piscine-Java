package edu.school21.sockets.server;

import edu.school21.sockets.services.UsersService;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;

@Component
public class Server {
    public Server(UsersService usersService) {
        this.usersService = usersService;
    }

    public void startListening(int port) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Ready to connect");
            while (true) {
                Socket clientSocket = serverSocket.accept();
                new ClientHandler(clientSocket, usersService);
                System.out.println("New connection!");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private final UsersService usersService;
}
