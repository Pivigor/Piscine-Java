package edu.school21.sockets.server;

import edu.school21.sockets.services.UsersService;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class ClientHandler extends Thread {
    public ClientHandler(Socket clientSocket, UsersService usersService) {
        this.clientSocket = clientSocket;
        this.usersService = usersService;
        this.mIt = 0;

        this.start();
    }

    @Override
    public void run() {
        try {
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            out.println("Hello from Server!<input>");

            Long userID = 0L;
            while (true) {
                String message = in.readLine();
                String login;
                String password;

                switch (message) {
                    case "signUp":
                        out.println("Enter username:<input>");
                        login = in.readLine();
                        out.println("Enter password:<input>");
                        password = in.readLine();
                        userID = usersService.signUp(login, password);
                        if (userID != 0L) {
                            out.println("Start messaging<input>");
                        } else {
                            out.println("Error! Such username already exists.<exit>");
                            clientSocket.close();
                            return;
                        }
                        continue;
                    case "signIn":
                        out.println("Enter username:<input>");
                        login = in.readLine();
                        out.println("Enter password:<input>");
                        password = in.readLine();
                        userID = usersService.signIn(login, password);
                        if (userID != 0L) {
                            out.println("Start messaging<input>");
                        } else {
                            out.println("Error! Wrong username or password.<exit>");
                            clientSocket.close();
                            return;
                        }
                        continue;
                    case "Exit":
                        out.println("You have left the chat.<exit>");
                        clientSocket.close();
                        return;
                    default:
                        if (userID != 0L) {
                            message = message.replace("<", "");
                            message = message.replace(">", "");
                            usersService.sendMessage(userID, message, LocalDateTime.now());
                        } else {
                            out.println("Command not found" + "<exit>");
                            clientSocket.close();
                            return;
                        }
                        break;
                }

                List<String> messages = new LinkedList<>(usersService.getMessages());
                if (mIt > messages.size()) {
                    mIt = 0;
                }

                for (; mIt < messages.size(); mIt++) {
                    out.println(messages.get(mIt));
                }
                out.println("<input>");
            }

        } catch (Exception ignored) {}

        try {
            clientSocket.close();
        } catch (Exception ignored) {}
    }

    private final Socket clientSocket;
    private final UsersService usersService;
    private int mIt;

}
